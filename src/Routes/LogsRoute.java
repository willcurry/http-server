package Routes;

import Server.HTTPRequest;
import Server.Response;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.IOException;

public class LogsRoute extends BaseRoute{
    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/logs"));
    }

    @Override
    public Response handleGET(HTTPRequest request) throws IOException {
        Response response = new Response();
        response.setHTTPVersion("HTTP/1.1");
        response.setStatusCode(401, "Unauthorized");
        if (hasAuthorisationHeader(request) && authorisationPasses(request)) {
            response.setStatusCode(200, "OK");
        }
        return response;
    }

    private boolean authorisationPasses(HTTPRequest request) throws IOException {
        String[] base64Auth = getAuthorisationHeader(request).split("\\s");
        com.sun.org.apache.xml.internal.security.Init.init();
        if (!base64Auth[2].equals("")) {
            try {
                String authAsString = new String(Base64.decode(base64Auth[2]), "UTF-8");
                return (authAsString.equals("admin:hunter2"));
            } catch (Base64DecodingException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private Boolean hasAuthorisationHeader(HTTPRequest request) throws IOException {
        return !getAuthorisationHeader(request).equals("");
    }

    private String getAuthorisationHeader(HTTPRequest request) throws IOException {
        for (String header : request.getHeaders()) {
            if (header.contains("Authorization: ")) return header;
        }
        return "";
    }
}
