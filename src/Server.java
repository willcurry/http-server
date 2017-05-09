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

    public void run() throws IOException, RequestParser.InvalidRequest {
        String request = input.readLine();
        String response = requestParser.parse(request).get("version") + " " + 200 + " OK";
        output.println(response);
    }

    public static void main(String[] args) throws IOException, RequestParser.InvalidRequest {
        ServerSocket serverSocket = new ServerSocket(5000);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            Server server = new Server(input, output);
            server.run();
            clientSocket.close();
        }
    }
}

