package zizaimengzhongue.com.github;

import com.sun.net.httpserver.HttpExchange;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Handler {
    public static void user(HttpExchange exchange) throws IOException {
        Connection conn = DB.getConnection();
        try {
            CallableStatement stmt = conn.prepareCall("select * from user");
            ResultSet rs = stmt.executeQuery();
            ArrayList<User> users = new ArrayList<User>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getInt("uid"), rs.getString("name"));
                users.add(user);
            }
            render(exchange, users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(HttpExchange exchange, User user) throws IOException {
        Connection conn = DB.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "insert into user (uid, name) value(" + user.getUid().toString() + " , \'" + user.getName() + "\')";
            stmt.execute(sql);
            output(exchange, "user " + user.getName() + " saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(HttpExchange exchange, User user) throws IOException {
        Connection conn = DB.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "delete from user where id=" + user.getId().toString();
            stmt.execute(sql);
            output(exchange, "user " + user.getId() + " is deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void render(HttpExchange exchange, ArrayList<User> users) throws IOException {
        output(exchange, JSON.toJSONString(users));
    }

    private static void output(HttpExchange exchange, String content) throws IOException {
        byte[] responseStr = content.getBytes();
        exchange.getResponseHeaders().add("Content-Type", "text/html;charset=utf-8");
        exchange.sendResponseHeaders(200, responseStr.length);
        OutputStream out = exchange.getResponseBody();
        out.write(responseStr);
        out.flush();
        out.close();
    }
}
