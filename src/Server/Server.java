package Server;

import java.io.BufferedReader;
import java.io.IOException;

public class Server {
    private final int port;
    private final HTTPServerManager serverManager;
    private Listener exitListener;

    public Server(int port, Listener exitListener, HTTPServerManager serverManager) {
        this.exitListener = exitListener;
        this.port = port;
        this.serverManager = serverManager;
    }

    public void run() throws IOException {
        serverManager.setUp(port);
        while (hasNotQuit()) {
            serverManager.sendResponse();
        }
    }

    private boolean hasNotQuit() {
        return !exitListener.hasBeenTriggered();
    }
}
