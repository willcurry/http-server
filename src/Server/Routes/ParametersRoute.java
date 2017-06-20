package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ParametersRoute extends BaseRoute {
    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/parameters"));
    }

    @Override
    public HTTPResponse handleGET(HTTPRequest request) throws IOException {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        setUpBody(response, request);
        return response;
    }

    private String decodeURI(String uri) throws UnsupportedEncodingException {
        return URLDecoder.decode(uri, "UTF-8");
    }

    public void setUpBody(HTTPResponse response, HTTPRequest request) throws UnsupportedEncodingException {
        String body = "";
        for (String parameter : request.getURIParameters())  {
            body += decodeURI(parameter);
        }
        response.setContent(body.getBytes());
    }
}
