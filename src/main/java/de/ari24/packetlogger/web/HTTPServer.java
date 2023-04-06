package de.ari24.packetlogger.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import de.ari24.packetlogger.PacketLogger;
import net.minecraft.resource.NamespaceResourceManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.commons.io.IOUtils;

import java.io.*;

import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Arrays;

public class HTTPServer {
    public static void start(int port) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServer.createContext("/", new Handler());

        PacketLogger.LOGGER.info("Starting http server on port " + port);
        httpServer.start();
    }

    static class Handler implements HttpHandler {
        static final java.util.Map<String, String> types = new java.util.HashMap<>(java.util.Map.of(
            "html", "text/html",
            "js", "text/javascript",
            "css", "text/css",
            "jpg", "image/jpeg",
            "png", "image/png",
            "ico", "image/x-icon",
            "json", "application/json"
        ));

        String prefix = "/";

        private static void sendNotFound(HttpExchange exchange) throws IOException {
            String response = "File not found";

            exchange.sendResponseHeaders(404, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            java.net.URI uri = exchange.getRequestURI();

            if(uri.toString().endsWith("/")) {
                uri = uri.resolve("index.html");
            }

            String path = uri.getPath();
            InputStreamReader reader = null;

            if(path.startsWith(prefix)) {
                try {
                    reader = new InputStreamReader(getClass().getResourceAsStream("/assets/packetlogger/web/" + path.substring(prefix.length())));
                } catch (Exception e) {
                    PacketLogger.LOGGER.error(e.toString());
                    sendNotFound(exchange);
                    return;
                }
            }

            String logOutput = "GET " + uri.toString();

            if(reader != null)
            {
                String filename = path.substring(path.lastIndexOf('/') + 1);
                String ext = filename.substring(filename.lastIndexOf('.') + 1);

                if(types.containsKey(ext)) {
                    logOutput += " " + types.get(ext);
                    exchange.getResponseHeaders()
                            .add("Content-Type", types.get(ext));
                }

                String content = IOUtils.toString(reader);

                logOutput += " 200 " + content.length();
                exchange.sendResponseHeaders(200, content.length());

                try (OutputStream out = exchange.getResponseBody()) {
                    out.write(content.getBytes());
                }
            } else {
                logOutput += " 404";
                String response = "File not found " + uri.toString();

                exchange.sendResponseHeaders(404, response.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }

            PacketLogger.LOGGER.info(logOutput);
        }
    }
}
