package Routes;

import Server.HTTPRequest;
import Server.Storage;
import Server.Response;

import java.io.IOException;

public class File1Route extends BaseRoute {
    private final Storage storage;

    public File1Route(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/file1"));
    }

    @Override
    public Response handleGET(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        if (storage.fileHasData("file1")) response.setContent(storage.readFile("file1"));
        return response;
    }
}
