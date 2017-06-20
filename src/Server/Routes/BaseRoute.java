package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;

import java.io.IOException;

public class BaseRoute {
    public HTTPResponse MethodNotAllowed() {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(405, "Method Not Allowed");
        return response;
    }

    public Boolean appliesTo(String uri) throws IOException {
        return false;
    }

    public HTTPResponse handleGET(HTTPRequest request) throws IOException {
        return MethodNotAllowed();
    }

    public HTTPResponse handlePOST(HTTPRequest request) throws IOException {
        return MethodNotAllowed();
    }

    public HTTPResponse handlePUT(HTTPRequest request) throws IOException {
        return MethodNotAllowed();
    }

    public HTTPResponse handleDELETE(HTTPRequest request) {
        return MethodNotAllowed();
    }

    public HTTPResponse handlePATCH(HTTPRequest request) throws IOException {
        return MethodNotAllowed();
    }

    public HTTPResponse handleOPTIONS(HTTPRequest request) {
        return MethodNotAllowed();
    }

    public HTTPResponse handleHEAD(HTTPRequest request) {
        return MethodNotAllowed();
    }
}
