import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    BufferedReader input;
    PrintWriter output;
    RequestParser requestParser;

    public Server(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
        requestParser = new RequestParser();
    }

    public void run() throws IOException {
        String response = requestParser.parse(input.readLine()).get("version") + " " + 200 + " OK";
        output.println(response);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Socket clientSocket = serverSocket.accept();
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
    }
}

