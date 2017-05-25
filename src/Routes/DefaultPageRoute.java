package Routes;

import Server.HTTPRequest;
import Server.Response;

public class DefaultPageRoute extends BaseRoute {
    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/");
    }

    @Override
    public Response handleGET(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        return response;
    }

    @Override
    public Response handleHEAD(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        return response;
    }
}
