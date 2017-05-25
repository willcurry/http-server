package Server;

import Routes.BaseRoute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
        String body = "";
        for (String parameter : request.getURIParameters()) body += decodeURI(parameter);
        response.setContent(body.getBytes());
        return response;
    }

    private String decodeURI(String uri) throws UnsupportedEncodingException {
        return URLDecoder.decode(uri, "UTF-8");
    }
}
