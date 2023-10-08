package zizaimengzhongue.com.github;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;


public class Route implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        User user = this.parseQuery(exchange.getRequestURI().getQuery());

        String uri = exchange.getRequestURI().getPath();
        switch (uri) {
            case "/user":
                Handler.user(exchange);
                break;
            case "/save":
                Handler.save(exchange, user);
                break;
            case "/delete":
                Handler.delete(exchange, user);
                break;
            default:
                this.output(exchange, "404/Not Found");
        }
    }

    private User parseQuery(String query) {
        User user = new User();
        if (query == null) {
            return user;
        }
        String[] kvs = query.split("&");
        for (String s : kvs) {
            String[] kv = s.split("=");
            switch (kv[0]) {
                case "id":
                    user.setId(Integer.parseInt(kv[1]));
                    break;
                case "uid":
                    user.setUid(Integer.parseInt(kv[1]));
                    break;
                case "name":
                    user.setName(kv[1]);
                    break;
            }
        }
        return user;
    }

    private void output(HttpExchange exchange, String content) throws IOException {
        byte[] responseStr = content.getBytes();
        exchange.getResponseHeaders().add("Content-Type", "text/html;charset=utf-8");
        exchange.sendResponseHeaders(200, responseStr.length);
        OutputStream out = exchange.getResponseBody();
        out.write(responseStr);
        out.flush();
        out.close();
    }
}

