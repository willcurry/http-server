package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final Router router;
    private final ServerSocket socket;
    private Listener exitListener;
    private ExecutorService executor;

    public Server(Listener exitListener, Router router, ServerSocket socket) {
        this.exitListener = exitListener;
        this.router = router;
        this.executor = Executors.newCachedThreadPool();
        this.socket = socket;
    }

    public void run() throws IOException {
        ResponseFinder responseFinder = new ResponseFinder(router);
        while (hasNotQuit()) {
            executor.execute(new RequestHandler(socket.accept(), responseFinder));
        }
    }

    private boolean hasNotQuit() {
        return !exitListener.hasBeenTriggered();
    }
}
