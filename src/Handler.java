import java.util.ArrayList;
import java.util.Map;

public class Handler {
    private ArrayList<Route> routes;

    public Handler() {
        routes = new ArrayList<>();
        routes.add(new DefaultPage());
        routes.add(new Form());
        routes.add(new MethodOptions2());
        routes.add(new MethodOptions());
        routes.add(new Redirect());
    }

    public Route handle(Map<String, String> parsedRequest) {
        for (Route route : routes) {
            if (route.appliesTo(parsedRequest.get("uri"))) {
                return route.withData(parsedRequest);
            }
        }
        return new FourOhFour();
    }
}
