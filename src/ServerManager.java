import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager implements HTTPServerManager {
    private BufferedReader input;
    private OutputStream output;
    private Socket clientSocket;
    private ServerSocket serverSocket;

    @Override
    public BufferedReader getInputStream() {
        return this.input;
    }

    @Override
    public void setUp(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void acceptRequests() throws IOException {
        this.clientSocket = serverSocket.accept();
        this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.output = clientSocket.getOutputStream();
    }

    @Override
    public void output(byte[] message) throws IOException {
        output.write(message);
        output.flush();
        clientSocket.close();
    }
}
