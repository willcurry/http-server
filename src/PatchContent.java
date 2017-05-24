import java.io.IOException;

public class PatchContent implements Route {
    private final Memory memory;
    private HTTPRequest request;

    public PatchContent() {
        this.memory = new Memory();
        this.memory.saveData("default content");
    }

    @Override
    public Response getResponse() throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        if (request.getVerb().equals("GET"))  {
            if (memory.hasData()) response.setContent(memory.getData());
        } else if (request.getVerb().equals("PATCH")) {
            memory.saveData(request.getBody());
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
