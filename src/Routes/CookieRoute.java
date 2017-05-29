package Routes;

import Server.HTTPRequest;
import Server.Response;
import Server.Storage;

public class CookieRoute extends BaseRoute {
    private final Storage storage;

    public CookieRoute(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/cookie");
    }

    @Override
    public Response handleGET(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        response.setHeader("Set-Cookie: " + storage.getData());
        response.setContent("Eat".getBytes());
        return response;
    }
}
