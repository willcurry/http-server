import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> parsedRequest = requestParser.parse(input.readLine());
        String response;
        if (parsedRequest.get("uri").equals("/")) {
            response = parsedRequest.get("version") + " " + 200 + " OK";
        } else if (parsedRequest.get("uri").equals("/form")) {
            response = parsedRequest.get("version") + " " + 200 + " OK";
        } else {
            response = parsedRequest.get("version") + " " + 404 + " Not Found";
        }
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
