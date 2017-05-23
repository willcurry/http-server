import java.io.IOException;
import java.util.ArrayList;

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

    public Route handle(HTTPRequest request) throws IOException {
        for (Route route : routes) {
            if (route.appliesTo(request.getURI())) {
                return route.withData(request);
            }
        }
        return new FourOhFour();
    }
}
