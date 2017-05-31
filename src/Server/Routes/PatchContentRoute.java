package Server.Routes;

import Server.HTTPRequest;
import Server.Response;
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
    public Response handleGET(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
         if (storage.fileHasData("patch-content.txt")){
            byte[] fileContents = storage.readFile("patch-content.txt");
            response.setContent(fileContents);
        }
        return response;
    }

    @Override
    public Response handlePATCH(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(204, "No Content");
        storage.writeToFile("patch-content.txt", request.getBody());
        return response;
    }
}
