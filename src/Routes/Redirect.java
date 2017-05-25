package Routes;

import Server.HTTPRequest;
import Server.Response;

public class Redirect extends BaseRoute {
    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/redirect");
    }

    @Override
    public Response handleGET(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(302, "Found");
        response.setHeader("Location: /");
        return response;
    }
}
