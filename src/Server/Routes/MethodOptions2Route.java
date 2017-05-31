package Server.Routes;

import Server.HTTPRequest;
import Server.Response;

public class MethodOptions2Route extends BaseRoute {
    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/method_options2");
    }

    @Override
    public Response handleGET(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        return response;
    }

    @Override
    public Response handleOPTIONS(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        response.setHeader("ALLOW: GET,OPTIONS");
        return response;
    }
}
