import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    public Map<String, String> parse(String request) {
        String[] requestLines = request.split("\\s+");
        Map<String, String> parsedRequest = new HashMap<String, String>();
        parsedRequest.put("type", requestLines[0]);
        parsedRequest.put("uri", requestLines[1]);
        parsedRequest.put("version", requestLines[2]);
        return parsedRequest;
    }
}
