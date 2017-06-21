package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final Router router;
    private final ServerSocket socket;
    private ExecutorService executor;

    public Server(Router router, int port) throws IOException {
        this.router = router;
        this.executor = Executors.newCachedThreadPool();
        this.socket = new ServerSocket(port);
    }

    public void run() throws IOException {
        ResponseFinder responseFinder = new ResponseFinder(router);
        while (true) {
            executor.execute(new RequestHandler(socket.accept(), responseFinder));
        }
    }
}
