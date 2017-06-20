package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;
import Server.Storage;

import java.io.IOException;

public class EatCookieRoute extends BaseRoute {
    private final Storage storage;

    public EatCookieRoute(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/eat_cookie"));
    }

    @Override
    public HTTPResponse handleGET(HTTPRequest request) throws IOException {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        if (getHeaderCookie(request).equals(storage.getData())) {
            response.setContent("mmmm chocolate".getBytes());
        }
        return response;
    }

    public String getHeaderCookie(HTTPRequest request) throws IOException {
        for (String header : request.getHeaders()) {
            if (header.contains("Cookie:")) {
                return header.split(":")[1].trim();
            }
        }
        return "";
    }
}
