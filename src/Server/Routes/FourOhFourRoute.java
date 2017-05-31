package Server.Routes;

import Server.HTTPRequest;
import Server.Response;

public class FourOhFourRoute extends BaseRoute {

    @Override
    public Response handleGET(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(404, "Not Found");
        return response;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return false;
    }
}
