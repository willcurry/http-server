package Server;

import Server.Routes.*;

import java.io.IOException;
import java.util.ArrayList;

public class Handler {
    private final Router router;

    public Handler(Router router) {
        this.router = router;

    }

    public Response handle(HTTPRequest request) throws IOException {
        for (BaseRoute route : router.allRoutes()) {
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
