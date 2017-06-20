package Server.Routes;

import Server.HTTPRequest;
import Server.HTTPResponse;
import Server.Logger;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class LogsRoute extends BaseRoute{
    @Override
    public Boolean appliesTo(String uri) {
        return (uri.equals("/logs"));
    }

    @Override
    public HTTPResponse handleGET(HTTPRequest request) throws IOException {
        HTTPResponse response = new HTTPResponse();
        response.setHTTPVersion("HTTP/1.1");
        if (hasAuthorisationHeader(request) && authorisationPasses(getAuthCode(request))) {
            response.setStatusCode(200, "OK");
            response.setContent(String.join("\n", Logger.getLogs()).getBytes());
            response.setHeader("WWW-Authenticate: " + getAuthCode(request));
        } else {
            response.setStatusCode(401, "Unauthorized");
            response.setHeader("WWW-Authenticate: Basic realm=\"User Visible Realm\"");
        }
        return response;
    }

    private boolean authorisationPasses(String authCode) {
        String authAsString = null;
        try {
            authAsString = new String(Base64.decode(authCode), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Base64DecodingException e) {
            e.printStackTrace();
        }
        return authAsString.equals("admin:hunter2");
    }

    private String getAuthCode(HTTPRequest request) throws IOException {
        String[] base64Auth = getAuthorisationHeader(request).split("\\s");
        com.sun.org.apache.xml.internal.security.Init.init();
        return base64Auth[2];

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
