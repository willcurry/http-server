public class FourOhFour implements Route {
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
    public Route withData(HTTPRequest request) {
        return this;
    }
}
