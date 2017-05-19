public class Form implements Request{
    @Override
    public Response getResponse() {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        return response;
    }
}
