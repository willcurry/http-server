package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;

public class FourOhFourRoute extends BaseRoute {

    @Override
    public HTTPResponse handleGET(HTTPRequest request) {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(404, "Not Found");
        return response;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return false;
    }
}
