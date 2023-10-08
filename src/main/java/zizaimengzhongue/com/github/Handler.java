package zizaimengzhongue.com.github;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Handler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        Connection conn = DB.getConnection();
        try {
            CallableStatement stmt = conn.prepareCall("select * from user");
            ResultSet rs = stmt.executeQuery();
            ArrayList<User> users = new ArrayList<User>();
            while(rs.next()) {
                User user = new User(rs.getInt("id"), rs.getInt("uid"), rs.getString("name"));
                users.add(user);
            }
            this.render(exchange, users);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void render(HttpExchange exchange, ArrayList<User> users) throws IOException {
        this.output(exchange, JSON.toJSONString(users));
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
