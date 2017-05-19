import java.util.Map;

public class Handler {
    public Request handle(Map<String, String> parsedRequest) {
        if (parsedRequest.get("uri").equals("/")) {
            return new DefaultPage();
        } else if (parsedRequest.get("uri").equals("/form")) {
            return new Form();
        } else if (parsedRequest.get("uri").equals("/method_options")) {
            return new MethodOptions();
        } else if (parsedRequest.get("uri").equals("/method_options2")) {
            return new MethodOptions2();
        } else if (parsedRequest.get("uri").equals("/redirect")) {
            return new Redirect();
        } else {
            return new FourOhFour();
        }
    }
}
