package zizaimengzhongue.com.github;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 1024);
            httpServer.createContext("/test", new Handler());
            httpServer.setExecutor(Executors.newFixedThreadPool(10));
            httpServer.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
