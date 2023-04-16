package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.mixin.TagPacketSerializerSerializedAccessor;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.SynchronizeTagsS2CPacket;

import java.util.ArrayList;
import java.util.List;

public class SynchronizeTagsS2CPacketHandler implements BasePacketHandler<SynchronizeTagsS2CPacket> {
    @Override
    public String name() {
        return "UpdateTags";
    }

    @Override
    public String url() {
        return "htthttps://wiki.vg/index.php?title=Protocol&oldid=18067#Update_Tags";
    }

    @Override
    public JsonObject serialize(SynchronizeTagsS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("length", packet.getGroups().size());

        List<JsonObject> tags = new ArrayList<>();

        packet.getGroups().forEach(((registryKey, serialized) -> {
            JsonObject child = new JsonObject();
            child.addProperty("tagType", registryKey.toString());

            // TODO: Casting fails, fix it
            /*((TagPacketSerializerSerializedAccessor) serialized).getContents().forEach((identifier, tag) -> {
                JsonObject jsonObject1 = new JsonObject();
                jsonObject1.addProperty("identifier", identifier.toString());
                jsonObject1.addProperty("length", tag.size());
                jsonObject1.add("contents", ConvertUtils.GSON_INSTANCE.toJsonTree(tag));
                tagArray.add(jsonObject1);
            });*/

            // child.add("tags", ConvertUtils.GSON_INSTANCE.toJsonTree(tagArray));
            child.addProperty("tags", "TODO");
            tags.add(child);
        }));

        jsonObject.add("tags", ConvertUtils.GSON_INSTANCE.toJsonTree(tags));
        return jsonObject;
    }
}
