package Routes;

import Server.HTTPRequest;
import Server.Response;
import Server.Storage;

import java.io.IOException;

public class EatCookieRoute extends BaseRoute {
    public EatCookieRoute(Storage storage) {

    }

    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/eat_cookie"));
    }

    @Override
    public Response handleGET(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        response.setContent("mmmm chocolate".getBytes());
        return response;
    }
}
