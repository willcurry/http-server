import java.util.ArrayList;
import java.util.Map;

public class Handler {
    private ArrayList<Request> requests;

    public Handler() {
        requests = new ArrayList<>();
        requests.add(new DefaultPage());
        requests.add(new Form());
        requests.add(new MethodOptions2());
        requests.add(new MethodOptions());
        requests.add(new Redirect());
    }

    public Request handle(Map<String, String> parsedRequest) {
        for (Request request : requests) {
            if (request.appliesTo(parsedRequest.get("uri"))) {
                return request;
            }
        }
        return new FourOhFour();
    }
}
