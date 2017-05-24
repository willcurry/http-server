import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager implements HTTPServerManager {
    private BufferedReader input;
    private PrintWriter output;
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
        this.output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void output(String message) throws IOException {
        output.print(message);
        output.flush();
        clientSocket.close();
    }
}
