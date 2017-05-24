import java.io.IOException;

public class Form implements Route {
    private HTTPRequest request;
    private Memory memory;

    public Form() {
        memory = new Memory();
    }

    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        try {
            if (request.getVerb().equals("GET"))  {
                if (memory.hasData()) response.setContent(memory.getData());
            } else if (request.getVerb().equals("POST") || request.getVerb().equals("PUT")) {
                memory.saveData(request.getBody());
            } else if (request.getVerb().equals("DELETE")) {
                memory.removeData();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
