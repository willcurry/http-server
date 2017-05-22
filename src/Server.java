import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
        int line;
        while ((line = input.read()) != -1) {
            buffer.append((char) line);
            if (buffer.length() >= 4) {
                if (buffer.substring(buffer.length() - 4).equals("\r\n\r\n")) break;
            }
        }
        buffer.append(getBody(buffer.toString()));
        Response response = handler.handle(requestParser.parse(buffer.toString())).getResponse();
        System.out.println(response.toString());
        output.print(response.toString());
        output.flush();
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
}
