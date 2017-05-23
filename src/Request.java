import java.io.IOException;
import java.io.Reader;

public class Request {
    private final Reader input;
    private final String requestString;
    private final String[] requestStringSplit;

    public Request(Reader input) throws IOException {
        this.input = input;
        this.requestString = readRequest();
        this.requestStringSplit = requestString.split("\\s+");
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
        return buffer.toString();
    }

    public String getVerb() throws IOException {
        return requestStringSplit[0];
    }

    public String getURI() throws IOException {
        return requestStringSplit[1];
    }

    public String getHTTPVersion() throws IOException {
        return requestStringSplit[2];
    }
}
