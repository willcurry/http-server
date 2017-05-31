package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final HTTPServerManager serverManager;
    private final ExecutorService executor;
    private Listener exitListener;

    public Server(int port, Listener exitListener, HTTPServerManager serverManager) {
        this.exitListener = exitListener;
        this.port = port;
        this.serverManager = serverManager;
        this.executor = Executors.newFixedThreadPool(5);
    }

    public void run() throws IOException {
        serverManager.setUp(port);
        while (hasNotQuit()) {
            executor.submit(() -> {
                try {
                    serverManager.sendResponse();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private boolean hasNotQuit() {
        return !exitListener.hasBeenTriggered();
    }
}
