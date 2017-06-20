package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;
import Server.Storage;

import java.io.IOException;

public class PartialContentRoute extends BaseRoute {
    private final Storage storage;
    private HTTPResponse contentIfHasContent;

    public PartialContentRoute(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/partial_content.txt"));
    }

    @Override
    public HTTPResponse handleGET(HTTPRequest request) throws IOException {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(206, "OK");
        setContentIfHasContent(response, request);
        return response;
    }

    private int[] findRange(HTTPRequest request) throws IOException {
        for (String header : request.getHeaders()) {
            if (header.contains("Range:")) return getRange(findRange(header));
        }
        return null;
    }

    private int[] findRange(String header) {
        String rangeString = header.trim().split("=")[1];
        String[] ints = rangeString.split("-", 2);
        int[] range = new int[2];
        range[0] = -1;
        range[1] = -1;
        if (!ints[0].isEmpty()) range[0] = Integer.parseInt(ints[0]);
        if (!ints[1].isEmpty()) range[1] = Integer.parseInt(ints[1]) + 1;
        return range;
    }

    private int[] getRange(int[] range) throws IOException {
        byte[] contentBytes = storage.readFile("partial_content.txt");
        if (range[1] <= 0) range[1] = contentBytes.length;
        if (range[0] < 0) {
            range[0] = contentBytes.length - range[1] + 1;
            range[1] = contentBytes.length;
        }
        return range;
    }

    private void setContentIfHasContent(HTTPResponse response, HTTPRequest request) throws IOException {
        if (storage.fileHasData("partial_content.txt")) {
             int[] range = findRange(request);
             byte[] content = storage.readFileWithRange("partial_content.txt", range[0], range[1]);
             response.setContent(content);
         }
    }
}
