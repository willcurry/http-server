import java.util.Map;

public class ResponseFactory {
    public String get(Map<String, String> parsedRequest) {
        if (parsedRequest.get("uri").equals("/")) {
            return parsedRequest.get("version") + " " + 200 + " OK";
        } else if (parsedRequest.get("uri").equals("/form")) {
            return parsedRequest.get("version") + " " + 200 + " OK";
        } else {
            return parsedRequest.get("version") + " " + 404 + " Not Found";
        }
    }
}
