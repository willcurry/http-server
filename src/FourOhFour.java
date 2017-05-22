import java.util.Map;

public class FourOhFour implements Request {
    public Response getResponse() {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(404, "Not Found");
        return response;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return false;
    }

    @Override
    public Request withData(Map<String, String> data) {
        return this;
    }
}
