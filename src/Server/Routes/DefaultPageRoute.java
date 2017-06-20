package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;
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
    public HTTPResponse handleGET(HTTPRequest request) throws IOException {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        response.setContentType("text/html");
        addAllFilesToBody(response);
        return response;
    }

    private void addAllFilesToBody(HTTPResponse response) throws IOException {
        String body = "";
        for (String fileName : storage.allFileNames()) {
            String urlToFile = "<a href=\"/" + fileName + "\">" + fileName + "</a>\n";
            body += urlToFile;
        }
        response.setContent(body.getBytes());
    }

    @Override
    public HTTPResponse handleHEAD(HTTPRequest request) {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        return response;
    }

}
