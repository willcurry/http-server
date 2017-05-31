package Server.Routes;

import Server.HTTPRequest;
import Server.Response;
import Server.Storage;

import java.io.IOException;
import java.nio.file.Path;

public class PublicFilesRoute extends BaseRoute {
    private final Storage storage;

    public PublicFilesRoute(Storage storage) {
        this.storage = storage;
    }

    @Override
    public Boolean appliesTo(String uri) throws IOException {
        String formattedURI = uri.substring(1);
        for (Path path : storage.allFiles()) {
            if (path.equals(storage.getPath(formattedURI))) return true;
        }
        return false;
    }

    @Override
    public Response handleGET(HTTPRequest request) throws IOException {
        String formattedURI = request.getURI().substring(1);
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(200, "OK");
        response.setContentType(findContentType(formattedURI));
        if (storage.fileHasData(formattedURI)) response.setContent(storage.readFile(formattedURI));
        return response;
    }

    private String findContentType(String uri) {
        switch (findFileExtension(uri)) {
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            default:
                return "text/plain";
        }
    }

    private String findFileExtension(String uri) {
        if (uri.contains(".")) return uri.split("\\.")[1];
        return "";
    }
}
