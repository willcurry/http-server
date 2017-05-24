import java.io.IOException;

public class PartialContent implements Route {
    private final Memory memory;
    private HTTPRequest request;

    public PartialContent(Memory memory) {
        this.memory = memory;
    }

    @Override
    public Response getResponse() throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(206, "OK");
        if (request.getVerb().equals("GET"))  {
            if (memory.hasData()) response.setContent(memory.getData());
        }
        return response;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/partial_content.txt"));
    }

    @Override
    public Route withData(HTTPRequest request) {
        this.request = request;
        return this;
    }
}
