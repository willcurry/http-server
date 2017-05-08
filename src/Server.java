import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    BufferedReader input;
    PrintWriter output;

    public Server(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    public void run() throws IOException {
        output.println(input.readLine());
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Socket clientSocket = serverSocket.accept();
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
    }
}

