package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager implements HTTPServerManager {
    private BufferedReader input;
    private OutputStream output;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private Handler handler;

    @Override
    public void setUp(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.handler = new Handler();
    }

    private Response getResponse(BufferedReader input) throws IOException {
        Request request = new Request(input);
        Logger.log(request);
        return handler.handle(request);
    }

    @Override
    public void sendResponse() throws IOException {
        clientSocket = serverSocket.accept();
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        output = clientSocket.getOutputStream();
        Response response = getResponse(input);
        output.write(response.asByteArray());
        output.flush();
        clientSocket.close();
    }
}
