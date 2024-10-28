import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SimpleHttpServer {
    private static final Logger logger = Logger.getLogger(SimpleHttpServer.class.getName());

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/hello", new HelloHandler());
        server.createContext("/greet", new GreetHandler());
        server.setExecutor(null); // create a default executor
        server.start();
        System.out.println("Server started on port 8080");
    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello World!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class GreetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            logRequest(exchange);
            String query = exchange.getRequestURI().getQuery();
            String name = "World";
            if (query != null && query.startsWith("name=")) {
                name = query.split("=")[1];
            }
            String response = "Hello, " + name + "!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private static void logRequest(HttpExchange exchange) {
        logger.info("Received request: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());
    }
}