package de.ari24.packetlogger.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import de.ari24.packetlogger.PacketLogger;

import java.io.File;
import java.io.IOException;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;

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
            File local = null;

            if(path.startsWith(prefix)) {
                URL url = getClass().getClassLoader().getResource("web/" + path.substring(prefix.length()));

                if (url == null) {
                    sendNotFound(exchange);
                    return;
                }

                local = new File(url.getFile());
            }

            String logOutput = "GET " + uri.toString();

            if(local != null && local.exists())
            {
                String filename = local.getName();
                String ext = filename.substring(filename.lastIndexOf('.') + 1);

                if(types.containsKey(ext)) {
                    logOutput += " " + types.get(ext);
                    exchange.getResponseHeaders()
                            .add("Content-Type", types.get(ext));
                }

                logOutput += " 200 " + local.length();
                exchange.sendResponseHeaders(200, local.length());

                try (OutputStream out = exchange.getResponseBody()) {
                    java.nio.file.Files.copy(local.toPath(), out);
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
