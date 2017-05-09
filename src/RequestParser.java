import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    public Map<String, String> parse(String request) throws InvalidRequest {
        String[] requestList = request.split("\\s+");
        Map<String, String> parsedRequest = new HashMap<String, String>();

        if (requestList.length != 3) {
            throw new InvalidRequest();
        }

        parsedRequest.put("type", requestList[0]);
        parsedRequest.put("uri", requestList[1]);
        parsedRequest.put("version", requestList[2]);
        return parsedRequest;
    }


    class InvalidRequest extends Exception {

    }
}
