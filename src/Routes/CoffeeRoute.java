package Routes;

import Server.HTTPRequest;
import Server.Response;

public class CoffeeRoute extends BaseRoute {
    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/coffee");
    }

    @Override
    public Response handleGET(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(418, "Teapot");
        response.setContent("I'm a teapot".getBytes());
        return response;
    }
}
