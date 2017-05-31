package Routes;

import Server.HTTPRequest;
import Server.Response;
import Server.Storage;

import java.io.IOException;

public class DefaultPageRoute extends BaseRoute {
    private final Storage storage;

    public DefaultPageRoute(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/");
    }

    @Override
    public Response handleGET(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        response.setContentType("text/html");
        String body = "";
        for (String fileName : storage.allFileNames()) {
            String urlToFile = "<a href=\"/" + fileName + "\">" + fileName + "</a>\n";
            body += urlToFile;
        }
        response.setContent(body.getBytes());
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
