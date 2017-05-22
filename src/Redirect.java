import java.util.Map;

public class Redirect implements Request {
    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(302, "Found");
        response.setHeader("Location: /");
        return response;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/redirect");
    }

    @Override
    public Request withData(Map<String, String> data) {
        return this;
    }
}
