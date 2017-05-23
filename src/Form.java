import java.io.IOException;

public class Form implements Route {
    private HTTPRequest data;
    private String temp = "Hello";

    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        try {
            if (data.getVerb().equals("GET"))  {
                response.setHeader("Content-Type: text/plain\nContent-Length: " + temp.length());
                response.setContent(temp);
            } else if (data.getVerb().equals("POST")) {
                temp = data.getVerb();
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
        this.data = request;
        return this;
    }
}
