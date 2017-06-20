package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;

public class CoffeeRoute extends BaseRoute {
    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/coffee");
    }

    @Override
    public HTTPResponse handleGET(HTTPRequest request) {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(418, "Teapot");
        response.setContent("I'm a teapot".getBytes());
        return response;
    }
}
