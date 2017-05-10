import java.util.Map;

public class ResponseFactory {
    public String get(Map<String, String> parsedRequest) {
        Response response = new Response();
        response.setHTTPVersion(parsedRequest.get("version"));
        if (parsedRequest.get("uri").equals("/")) {
            response.setStatusCode(200, "OK");
            return response.toString();
        } else if (parsedRequest.get("uri").equals("/form")) {
            response.setStatusCode(200, "OK");
            return response.toString();
        } else if (parsedRequest.get("uri").equals("/method_options")) {
            response.setStatusCode(200, "OK");
            response.setHeader("ALLOW: GET,HEAD,POST,OPTIONS,PUT");
            return response.toString();
        } else if (parsedRequest.get("uri").equals("/method_options2")) {
            response.setStatusCode(200, "OK");
            response.setHeader("ALLOW: GET,OPTIONS");
            return response.toString();
        } else if (parsedRequest.get("uri").equals("/redirect")) {
            response.setStatusCode(302, "Found");
            response.setHeader("Location: /");
            return response.toString();
        } else {
            response.setStatusCode(404, "Not Found");
            return response.toString();
        }
    }
}
