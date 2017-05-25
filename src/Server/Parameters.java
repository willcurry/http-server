package Server;

import Routes.BaseRoute;

import java.io.IOException;

public class Parameters extends BaseRoute {
    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/parameters"));
    }

    @Override
    public Response handleGET(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        return response;
    }
}
