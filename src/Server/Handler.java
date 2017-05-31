package Server;

import Server.Routes.*;

import java.io.IOException;
import java.util.ArrayList;

public class Handler {
    private ArrayList<BaseRoute> routes;

    public Handler() {
        Storage storage = new Storage("/Users/willcurry/cob_spec/public/");
        routes = new ArrayList<>();
        routes.add(new DefaultPageRoute(storage));
        routes.add(new FormRoute(storage));
        routes.add(new MethodOptions2Route());
        routes.add(new MethodOptionsRoute());
        routes.add(new RedirectRoute());
        routes.add(new PatchContentRoute(storage));
        routes.add(new PartialContentRoute(storage));
        routes.add(new TeaRoute());
        routes.add(new CoffeeRoute());
        routes.add(new ParametersRoute());
        routes.add(new PublicFilesRoute(storage));
        Storage cookieStorage = new Storage("/Users/willcurry/cob_spec/public/");
        routes.add(new EatCookieRoute(cookieStorage));
        routes.add(new CookieRoute(cookieStorage));
        routes.add(new LogsRoute());
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
