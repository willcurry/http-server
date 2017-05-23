import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

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
            if (header.contains("Content-Length:")) return findBody();
        }
        return "";
    }

    private String findBody() throws IOException {
        for (int i=0; i < requestLines.length; i++) {
            if (requestLines[i].equals("")) {
                return arrayToString(Arrays.copyOfRange(requestLines, i++, requestLines.length));
            }
        }
        return "";
    }

    private String arrayToString(String[] array) {
        String arrayString = "";
        for (String line : array) {
            if (line.equals("")) continue;
            arrayString += line + "\n";
        }
        return arrayString.substring(0, arrayString.length() - 1);
    }
}
