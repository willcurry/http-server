import java.io.IOException;
import java.util.ArrayList;

public class Handler {
    private ArrayList<Route> routes;

    public Handler() {
        Memory memory = new Memory("/Users/willcurry/cob_spec/public/");
        routes = new ArrayList<>();
        routes.add(new DefaultPage());
        routes.add(new Form(memory));
        routes.add(new MethodOptions2());
        routes.add(new MethodOptions());
        routes.add(new Redirect());
        routes.add(new PatchContent(memory));
        routes.add(new PartialContent(memory));
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
