import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Server {
    BufferedReader input;
    PrintWriter output;
    RequestParser requestParser;
    Handler handler;

    public Server(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
        requestParser = new RequestParser();
        handler = new Handler();
    }

    public void run() throws IOException, RequestParser.InvalidRequest {
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = input.readLine()) != null) {
            if (line.equals("")) break;
            buffer.append(line + "\n");
        }
        buffer.append(getBody(buffer.toString()));
        Response response = handler.handle(requestParser.parse(buffer.toString())).getResponse();
        output.println(response.toString());
    }

    private String contentLength(String header) {
        String[] requestList = header.split("\\s+");
        List<String> headersList = Arrays.asList(requestList);
        int index = headersList.indexOf("Content-Length:") + 1;
        return requestList[index];
    }

    private String getBody(String header) throws IOException {
        if (header.contains("Content-Length")) {
            int amount = Integer.valueOf(contentLength(header));
            char[] content = new char[amount];
            input.read(content, 0, amount);
            return String.valueOf(content);
        }
        return "";
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
