package de.ari24.packetlogger.utils;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Constructor;
import java.util.List;

public class ReflectionUtils {
    public static <T> Constructor<T> findMatchingConstructor(Class<T> clazz, List<Object> parameters) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == parameters.size()) {
                boolean match = true;
                for (int i = 0; i < parameterTypes.length; i++) {
                    Object parameter = parameters.get(i);

                    if (parameter == null) {
                        match = false;
                        break;
                    }

                    if (parameterTypes[i].isPrimitive() && ClassUtils.isPrimitiveWrapper(parameter.getClass())) {
                        Class<?> primitiveType = ClassUtils.wrapperToPrimitive(parameter.getClass());

                        if (!parameterTypes[i].equals(primitiveType)) {
                            match = false;
                            break;
                        }
                    } else if (!parameterTypes[i].isInstance(parameter)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    return (Constructor<T>) constructor;
                }
            }
        }
        return null;
    }
}
