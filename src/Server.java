import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Server {
    BufferedReader input;
    PrintWriter output;
    Handler handler;
    Listener exitListener;

    public Server(BufferedReader input, PrintWriter output, Listener exitListener) {
        this.input = input;
        this.output = output;
        handler = new Handler();
        this.exitListener = exitListener;
    }

    private void handleRequests() throws IOException {
        Request request = new Request(input);
        Response response = handler.handle(request).getResponse();
        output.print(response.toString());
        output.flush();
    }

    public void run() throws IOException {
        while (hasNotQuit()) {
            handleRequests();
        }
    }

    private boolean hasNotQuit() {
        return !exitListener.hasBeenTriggered();
    }
}
