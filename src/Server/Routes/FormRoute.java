package Server.Routes;

import Server.HTTPRequest;
import Server.Response;
import Server.Storage;

import java.io.IOException;

public class FormRoute extends BaseRoute {
    private HTTPRequest request;
    private Storage storage;

    public FormRoute(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/form");
    }

    @Override
    public Response handleGET(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        if (storage.hasData()) response.setContent(storage.getData().getBytes());
        return response;
    }

    @Override
    public Response handlePOST(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        storage.saveData(request.getBody());
        return response;
    }

    @Override
    public Response handlePUT(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        storage.saveData(request.getBody());
        return response;
    }

    @Override
    public Response handleDELETE(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        storage.removeData();
        return response;
    }
}
