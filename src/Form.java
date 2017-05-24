import java.io.IOException;

public class Form implements Route {
    private HTTPRequest request;
    private Memory memory;

    public Form(Memory memory) {
        this.memory = memory;
    }

    @Override
    public Response getResponse() throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        if (request.getVerb().equals("GET"))  {
            if (memory.hasData()) response.setContent(memory.getData());
        } else if (request.getVerb().equals("POST") || request.getVerb().equals("PUT")) {
            memory.saveData(request.getBody());
        } else if (request.getVerb().equals("DELETE")) {
            memory.removeData();
        }
        return response;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/form");
    }

    @Override
    public Route withData(HTTPRequest request) {
        this.request = request;
        return this;
    }
}
