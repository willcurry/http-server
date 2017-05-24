import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Request implements HTTPRequest {
    private final Reader input;
    private final String requestString;
    private final String[] requestStringSplit;
    private final String[] requestLines;

    public Request(Reader input) throws IOException {
        this.input = input;
        this.requestString = readRequest();
        this.requestStringSplit = requestString.split("\\s+");
        requestLines = requestString.split("\n");
    }

    private String readRequest() throws IOException {
        StringBuilder buffer = new StringBuilder();
        int line;
        while ((line = input.read()) != -1) {
            buffer.append((char) line);
            if (buffer.length() >= 4) {
                if (buffer.substring(buffer.length() - 4).equals("\r\n\r\n")) break;
            }
        }
        System.out.println(buffer.toString());
        return buffer.toString();
    }

    @Override
    public String getVerb() throws IOException {
        return requestStringSplit[0];
    }

    @Override
    public String getURI() throws IOException {
        return requestStringSplit[1];
    }

    @Override
    public String getHTTPVersion() throws IOException {
        return requestStringSplit[2];
    }

    @Override
    public ArrayList<String> getHeaders() {
        ArrayList<String> headers = new ArrayList<>();
        for (String line : requestLines) {
            if (line.equals("")) return headers;
            headers.add(line);
        }
        return headers;
    }

    @Override
    public String getBody() throws IOException {
        for (String header : getHeaders()) {
            if (header.contains("Content-Length:")) return findBody(header);
        }
        return "";
    }

    private String findBody(String header) throws IOException {
        int length = Integer.valueOf(header.split("\\s+")[1]);
        char[] content = new char[length];
        input.read(content, 0, length);
        return String.valueOf(content);
    }
}
