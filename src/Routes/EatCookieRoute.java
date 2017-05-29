package Routes;

import Server.HTTPRequest;
import Server.Response;
import Server.Storage;
import sun.security.x509.X509CertInfo;

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
    public Response handleGET(HTTPRequest request) throws IOException {
        Response response = new Response();
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
