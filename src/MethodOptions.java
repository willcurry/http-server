public class MethodOptions implements Route {
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
    public Route withData(HTTPRequest request) {
        return this;
    }
}
