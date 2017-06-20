package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;

public class MethodOptions2Route extends BaseRoute {
    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/method_options2");
    }

    @Override
    public HTTPResponse handleGET(HTTPRequest request) {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        return response;
    }

    @Override
    public HTTPResponse handleOPTIONS(HTTPRequest request) {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        response.setHeader("ALLOW: GET,OPTIONS");
        return response;
    }
}
