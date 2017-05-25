package Routes;

import Server.HTTPRequest;
import Server.Memory;
import Server.Response;

import java.io.IOException;

public class PartialContent extends BaseRoute {
    private final Memory memory;

    public PartialContent(Memory memory) {
        this.memory = memory;
    }

    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/partial_content.txt"));
    }

    @Override
    public Response handleGET(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(206, "OK");
         if (memory.fileHasData("partial_content.txt")) {
             int[] range = findRange(request);
             byte[] content = memory.readFileWithRange("partial_content.txt", range[0], range[1]);
             response.setContent(content);
         }
        return response;
    }

    private int[] findRange(HTTPRequest request) throws IOException {
        for (String header : request.getHeaders()) {
            if (header.contains("Range:")) return getRange(header);
        }
        return null;
    }

    private int[] getRange(String header) throws IOException {
        byte[] content = memory.readFile("partial_content.txt");
        String rangeString = header.trim().split("=")[1];
        String[] ints = rangeString.split("-", 2);
        int[] range = new int[2];
        range[0] = -1;
        range[1] = -1;
        if (!ints[0].isEmpty()) range[0] = Integer.parseInt(ints[0]);
        if (!ints[1].isEmpty()) range[1] = Integer.parseInt(ints[1]) + 1;
        if (range[1] <= 0) range[1] = content.length;
        if (range[0] < 0) {
            range[0] = content.length - range[1] + 1;
            range[1] = content.length;
        }
        return range;
    }
}
