import java.io.IOException;

public class PatchContent implements Route {
    private final Memory memory;
    private HTTPRequest request;

    public PatchContent() {
        this.memory = new Memory();
    }

    @Override
    public Response getResponse() throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        if (request.getVerb().equals("GET"))  {
            if (memory.fileHasData("/Users/willcurry/cob_spec/public/patch-content.txt")) response.setContent(memory.readFile("/Users/willcurry/cob_spec/public/patch-content.txt"));
        } else if (request.getVerb().equals("PATCH")) {
            memory.writeToFile("/Users/willcurry/cob_spec/public/patch-content.txt", request.getBody());
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
