package Routes;

import Server.HTTPRequest;
import Server.Storage;
import Server.Response;

import java.io.IOException;

public class TextFileRoute extends BaseRoute {
    private final Storage storage;

    public TextFileRoute(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/text-file.txt"));
    }

    @Override
    public Response handleGET(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        if (storage.fileHasData("text-file.txt")) response.setContent(storage.readFile("text-file.txt"));
        return response;
    }
}
