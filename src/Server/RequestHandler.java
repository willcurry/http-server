package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler implements Runnable {
    private final Socket socket;
    private final ResponseFinder responseFinder;

    public RequestHandler(Socket socket, ResponseFinder responseFinder) {
        this.socket = socket;
        this.responseFinder = responseFinder;
    }

    private HTTPResponse getResponse(BufferedReader input) throws IOException {
        Request request = new Request(input);
        Logger.log(request);
        return responseFinder.find(request);
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            OutputStream output = socket.getOutputStream();
            HTTPResponse response = getResponse(input);
            output.write(response.asByteArray());
            output.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
