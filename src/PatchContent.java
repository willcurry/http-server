import java.io.IOException;

public class PatchContent implements Route {
    private final Memory memory;
    private HTTPRequest request;

    public PatchContent(Memory memory) {
        this.memory = memory;
    }

    @Override
    public Response getResponse() throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        if (request.getVerb().equals("GET"))  {
            if (memory.fileHasData("patch-content.txt")){
                String fileContents = Util.makeString(memory.readFile("patch-content.txt"));
                response.setContent(fileContents);
            }
        } else if (request.getVerb().equals("PATCH")) {
            memory.writeToFile("patch-content.txt", request.getBody());
            response.setStatusCode(204, "No Content");
        }
        return response;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/patch-content.txt"));
    }

    @Override
    public Route withData(HTTPRequest request) {
        this.request = request;
        return this;
    }
}
