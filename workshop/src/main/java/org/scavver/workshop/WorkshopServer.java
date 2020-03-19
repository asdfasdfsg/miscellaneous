package org.scavver.workshop;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkshopServer {

    private static final Logger LOGGER = Logger.getLogger(WorkshopServer.class.getName());

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", Config.SERVER_PORT), 0);

        server.createContext("/", new MainHttpHandler());

        ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        server.setExecutor(tpe);

        server.start();

        LOGGER.info("Server started on " + server.getAddress().getHostString() + ":" + server.getAddress().getPort());
    }

    static {
        try {
            Handler handler = new FileHandler("server.log");
            handler.setLevel(Level.ALL);
            LOGGER.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
