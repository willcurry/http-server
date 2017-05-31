package Routes;

import Server.HTTPRequest;
import Server.Response;

public class LogsRoute extends BaseRoute{
    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/logs"));
    }

    @Override
    public Response handleGET(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(401, "Unauthorized");
        return response;
    }
}
