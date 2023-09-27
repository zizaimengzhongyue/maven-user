package zizaimengzhongue.com.github;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class Handler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        StringBuilder responseText = new StringBuilder();
        responseText.append("hello,world");
        byte[] responseStr = responseText.toString().getBytes();
        exchange.getResponseHeaders().add("Content-Type:", "text/html;charset=utf-8");
        exchange.sendResponseHeaders(200, responseStr.length);
        OutputStream out = exchange.getResponseBody();
        out.write(responseStr);
        out.flush();
        out.close();
    }
}
