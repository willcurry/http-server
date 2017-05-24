import java.io.IOException;

public class Form implements Route {
    private HTTPRequest request;
    private String temp = "Hello";

    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        try {
            if (request.getVerb().equals("GET"))  {
                response.setHeader("Content-Type: text/plain\nContent-Length: " + temp.length());
                response.setContent(temp);
            } else if (request.getVerb().equals("POST")) {
                temp = request.getBody();
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
