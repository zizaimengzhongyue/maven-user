package zizaimengzhongyue.com.github;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 1024);
            httpServer.createContext("/user", new Route());
            httpServer.createContext("/save", new Route());
            httpServer.createContext("/delete", new Route());
            httpServer.setExecutor(Executors.newFixedThreadPool(10));
            httpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
