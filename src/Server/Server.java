package Server;

import java.io.IOException;

public class Server {
    private final int port;
    private final HTTPServerManager serverManager;
    private final Router router;
    private Listener exitListener;

    public Server(int port, Listener exitListener, HTTPServerManager serverManager, Router router) {
        this.exitListener = exitListener;
        this.port = port;
        this.serverManager = serverManager;
        this.router = router;
    }

    public void run() throws IOException {
        serverManager.setUp(port, router);
        while (hasNotQuit()) {
            serverManager.sendResponse();
        }
    }

    private boolean hasNotQuit() {
        return !exitListener.hasBeenTriggered();
    }
}
