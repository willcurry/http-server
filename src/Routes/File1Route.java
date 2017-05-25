package Routes;

import Server.HTTPRequest;
import Server.Memory;
import Server.Response;

import java.io.IOException;

public class File1Route extends BaseRoute {
    private final Memory memory;

    public File1Route(Memory memory) {
        this.memory = memory;
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
        if (memory.fileHasData("file1")) response.setContent(memory.readFile("file1"));
        return response;
    }
}
