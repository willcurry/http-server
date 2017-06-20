package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;
import Server.Storage;

import java.io.IOException;

public class PatchContentRoute extends BaseRoute {
    private final Storage storage;

    public PatchContentRoute(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/patch-content.txt"));
    }

    @Override
    public HTTPResponse handleGET(HTTPRequest request) throws IOException {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        setContentIfHasContent(response);
        return response;
    }

    @Override
    public HTTPResponse handlePATCH(HTTPRequest request) throws IOException {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(204, "No Content");
        storage.writeToFile("patch-content.txt", request.getBody());
        return response;
    }

    private void setContentIfHasContent(HTTPResponse response) throws IOException {
        if (storage.fileHasData("patch-content.txt")){
            byte[] fileContents = storage.readFile("patch-content.txt");
            response.setContent(fileContents);
        }
    }
}
