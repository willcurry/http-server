package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;
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
    public HTTPResponse handleGET(HTTPRequest request) {
        storage.saveData(formatQueryParameters(request.getURIParameters()));
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        response.setHeader("Set-Cookie: " + storage.getData());
        response.setContent("Eat".getBytes());
        return response;
    }

    private String formatQueryParameters(String[] uriParameters) {
        return uriParameters[0];
    }
}
