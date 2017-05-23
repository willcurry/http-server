import java.io.File;
import java.util.Map;

public class Form implements Request{
    private Map<String, String> data;
    private String temp = "Hello";
    private File content;

    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        if (data.get("verb").equals("GET"))  {
            response.setHeader("Content-Type: text/plain\nContent-Length: " + temp.length());
            response.setContent(temp);
        } else if (data.get("verb").equals("POST")) {
            temp = data.get("body");
        }
        return response;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/form");
    }

    @Override
    public Request withData(Map<String, String> data) {
        this.data = data;
        return this;
    }
}
