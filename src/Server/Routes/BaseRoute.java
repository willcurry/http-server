package Server.Routes;

import Server.HTTPRequest;
import Server.Response;

import java.io.IOException;

public class BaseRoute {
    public Response MethodNotAllowed() {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(405, "Method Not Allowed");
        return response;
    }

    public Boolean appliesTo(String uri) throws IOException {
        return false;
    }

    public Response handleGET(HTTPRequest request) throws IOException {
        return MethodNotAllowed();
    }

    public Response handlePOST(HTTPRequest request) throws IOException {
        return MethodNotAllowed();
    }

    public Response handlePUT(HTTPRequest request) throws IOException {
        return MethodNotAllowed();
    }

    public Response handleDELETE(HTTPRequest request) {
        return MethodNotAllowed();
    }

    public Response handlePATCH(HTTPRequest request) throws IOException {
        return MethodNotAllowed();
    }

    public Response handleOPTIONS(HTTPRequest request) {
        return MethodNotAllowed();
    }

    public Response handleHEAD(HTTPRequest request) {
        return MethodNotAllowed();
    }
}
