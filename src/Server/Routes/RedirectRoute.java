package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;

public class RedirectRoute extends BaseRoute {
    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/redirect");
    }

    @Override
    public HTTPResponse handleGET(HTTPRequest request) {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(302, "Found");
        response.setHeader("Location: /");
        return response;
    }
}
