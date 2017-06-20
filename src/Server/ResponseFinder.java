package Server;

import Server.Routes.BaseRoute;
import Server.Routes.FourOhFourRoute;

import java.io.IOException;

public class ResponseFinder {
    private final Router router;

    public ResponseFinder(Router router) {
        this.router = router;

    }

    public HTTPResponse find(HTTPRequest request) throws IOException {
        for (BaseRoute route : router.allRoutes()) {
            if (route.appliesTo(request.getURI())) {
                return findResponseForRequest(request, route);
            }
        }
        return new FourOhFourRoute().handleGET(request);
    }

    private HTTPResponse findResponseForRequest(HTTPRequest request, BaseRoute route) throws IOException {
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
