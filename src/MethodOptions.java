import java.util.Map;

public class MethodOptions implements Request {
    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        response.setHeader("ALLOW: GET,HEAD,POST,OPTIONS,PUT");
        return response;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/method_options");
    }

    @Override
    public Request withData(Map<String, String> data) {
        return this;
    }
}
