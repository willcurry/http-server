package Routes;

import Server.HTTPRequest;
import Server.Memory;
import Server.Response;

import java.io.IOException;

public class PatchContentRoute extends BaseRoute {
    private final Memory memory;

    public PatchContentRoute(Memory memory) {
        this.memory = memory;
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
         if (memory.fileHasData("patch-content.txt")){
            byte[] fileContents = memory.readFile("patch-content.txt");
            response.setContent(fileContents);
        }
        return response;
    }

    @Override
    public Response handlePATCH(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(204, "No Content");
        memory.writeToFile("patch-content.txt", request.getBody());
        return response;
    }
}
