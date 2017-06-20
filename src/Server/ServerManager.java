package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerManager implements HTTPServerManager {
    private BufferedReader input;
    private OutputStream output;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private Handler handler;
    private ExecutorService executor;

    @Override
    public void setUp(int port, Router router) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.handler = new Handler(router);
        this.executor = Executors.newFixedThreadPool(1);
    }

    private HTTPResponse getResponse(BufferedReader input) throws IOException {
        Request request = new Request(input);
        Logger.log(request);
        return handler.handle(request);
    }

    @Override
    public void sendResponse() throws IOException {
        executor.execute(() -> {
            try {
                clientSocket = serverSocket.accept();
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                output = clientSocket.getOutputStream();
                HTTPResponse response = getResponse(input);
                output.write(response.asByteArray());
                output.flush();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
