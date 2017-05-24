import java.io.BufferedReader;
import java.io.IOException;

public class Server {
    private final int port;
    private final HTTPServerManager serverManager;
    private Handler handler;
    private Listener exitListener;

    public Server(int port, Listener exitListener, HTTPServerManager serverManager) {
        handler = new Handler();
        this.exitListener = exitListener;
        this.port = port;
        this.serverManager = serverManager;
    }

    private Response getResponse(BufferedReader input) throws IOException {
        Request request = new Request(input);
        return handler.handle(request).getResponse();
    }

    public void run() throws IOException {
        serverManager.setUp(port);
        while (hasNotQuit()) {
            serverManager.acceptRequests();
            Response response = getResponse(serverManager.getInputStream());
            serverManager.output(response.toString());
        }
    }

    private boolean hasNotQuit() {
        return !exitListener.hasBeenTriggered();
    }
}
