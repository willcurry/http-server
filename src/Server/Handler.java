package Server;

import Routes.*;

import java.io.IOException;
import java.util.ArrayList;

public class Handler {
    private ArrayList<BaseRoute> routes;

    public Handler() {
        Memory memory = new Memory("/Users/willcurry/cob_spec/public/");
        routes = new ArrayList<>();
        routes.add(new DefaultPageRoute());
        routes.add(new FormRoute(memory));
        routes.add(new MethodOptions2Route());
        routes.add(new MethodOptionsRoute());
        routes.add(new RedirectRoute());
        routes.add(new PatchContentRoute(memory));
        routes.add(new PartialContentRoute(memory));
        routes.add(new File1Route(memory));
        routes.add(new TextFileRoute(memory));
        routes.add(new TeaRoute());
        routes.add(new CoffeeRoute());
        routes.add(new Parameters());
    }

    public Response handle(HTTPRequest request) throws IOException {
        for (BaseRoute route : routes) {
            if (route.appliesTo(request.getURI())) {
                return findResponseForRequest(request, route);
            }
        }
        return new FourOhFourRoute().handleGET(request);
    }

    private Response findResponseForRequest(HTTPRequest request, BaseRoute route) throws IOException {
        switch (request.getVerb()) {
            case "GET":
                return route.handleGET(request);
            case "POST":
                return route.handlePOST(request);
            case "PUT":
                return route.handlePUT(request);
            case "DELETE":
                return route.handleDELETE(request);
            case "PATCH":
                return route.handlePATCH(request);
            case "OPTIONS":
                return route.handleOPTIONS(request);
            case "HEAD":
                return route.handleHEAD(request);
        }
        return route.MethodNotAllowed();
    }
}
