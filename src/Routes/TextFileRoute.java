package Routes;

import Server.HTTPRequest;
import Server.Memory;
import Server.Response;

import java.io.IOException;

public class TextFileRoute extends BaseRoute {
    private final Memory memory;

    public TextFileRoute(Memory memory) {
        this.memory = memory;
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
        if (memory.fileHasData("text-file.txt")) response.setContent(memory.readFile("text-file.txt"));
        return response;
    }
}
