import java.io.IOException;

public class Form extends BaseRoute {
    private HTTPRequest request;
    private Memory memory;

    public Form(Memory memory) {
        this.memory = memory;
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
        if (memory.hasData()) response.setContent(memory.getData().getBytes());
        return response;
    }

    @Override
    public Response handlePOST(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        memory.saveData(request.getBody());
        return response;
    }

    @Override
    public Response handlePUT(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        memory.saveData(request.getBody());
        return response;
    }

    @Override
    public Response handleDELETE(HTTPRequest request) {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        memory.removeData();
        return response;
    }
}
