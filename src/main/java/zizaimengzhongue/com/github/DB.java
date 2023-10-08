package zizaimengzhongue.com.github;

import java.sql.DriverManager;
import java.sql.Connection;

public class DB {
    public static final String URL = "jdbc:mysql://localhost:3306/test";
    public static final String User = "root";
    public static final String Password = "123456";
    private static Connection conn = null;
    static{
        try {
            // class.forName 语法：找到 com.mysql.jdbc.Driver 类初始化静态变量和执行静态方法
            // 在 com.mysql.jdbc.Driver 类静态方法中包含了注册到 DriverManager 的操作，
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, User, Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
