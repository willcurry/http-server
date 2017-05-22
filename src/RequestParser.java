import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    public Map<String, String> parse(String request) throws InvalidRequest {
        String[] requestList = request.split("\\s+");
        Map<String, String> parsedRequest = new HashMap<String, String>();

        if (requestList.length < 3) {
            throw new InvalidRequest();
        }

        parsedRequest.put("verb", requestList[0]);
        parsedRequest.put("uri", requestList[1]);
        parsedRequest.put("version", requestList[2]);
        parsedRequest.put("body", getBody(request));
        return parsedRequest;
    }

    private String getBody(String request) {
        String[] lines = request.split("\n");
        return lines[lines.length -1];
    }

    class InvalidRequest extends Exception {

    }
}
