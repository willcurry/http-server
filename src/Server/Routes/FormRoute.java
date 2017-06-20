package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;
import Server.Storage;

import java.io.IOException;

public class FormRoute extends BaseRoute {
    private Storage storage;

    public FormRoute(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return uri.equals("/form");
    }

    @Override
    public HTTPResponse handleGET(HTTPRequest request) {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        if (storage.hasData()) response.setContent(storage.getData().getBytes());
        return response;
    }

    @Override
    public HTTPResponse handlePOST(HTTPRequest request) throws IOException {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        storage.saveData(request.getBody());
        return response;
    }

    @Override
    public HTTPResponse handlePUT(HTTPRequest request) throws IOException {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        storage.saveData(request.getBody());
        return response;
    }

    @Override
    public HTTPResponse handleDELETE(HTTPRequest request) {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        storage.removeData();
        return response;
    }
}
