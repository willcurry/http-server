import java.util.Map;

public class DefaultPage implements  Request {
    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        return response;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/");
    }

    @Override
    public Request withData(Map<String, String> data) {
        return this;
    }
}
