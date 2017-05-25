package Routes;

import Server.HTTPRequest;
import Server.Response;

import java.io.IOException;

public class TextFile extends BaseRoute {
    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/text-file.txt"));
    }

    @Override
    public Response handleGET(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        return response;
    }
}
