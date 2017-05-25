package Tests;

import Server.HTTPRequest;

import java.io.IOException;
import java.util.ArrayList;

public class FakeRequest implements HTTPRequest {
    private final String uri;
    private final String body;
    private final String httpVersion;
    private final String verb;
    private ArrayList<String> headers;

    public FakeRequest(String verb, String uri, String httpVersion, String body) {
        this.uri = uri;
        this.verb = verb;
        this.httpVersion = httpVersion;
        this.body = body;
    }

    @Override
    public String getVerb() throws IOException {
        return this.verb;
    }

    @Override
    public String getURI() throws IOException {
        return parseURI(uri)[0];
    }

    @Override
    public String getHTTPVersion() throws IOException {
        return this.httpVersion;
    }

    @Override
    public ArrayList<String> getHeaders() throws IOException {
        return headers;
    }

    @Override
    public String getBody() throws IOException {
        return this.body;
    }

    @Override
    public String[] getURIParameters() {
        String[] parameters = parseURI(uri)[1].split("&");
        return formatParameters(parameters);
    }

    private String[] formatParameters(String[] parameters) {
        String[] formattedParameters = new String[parameters.length];
        for (int i=0; i < parameters.length; i++) {
            String[] splitParameter = parameters[i].split("=");
            formattedParameters[i] = splitParameter[0] + " = " + splitParameter[1];
        }
        return formattedParameters;
    }

    private String[] parseURI(String uri) {
        String[] uriWithNoParameters = new String[1];
        uriWithNoParameters[0] = uri;
        if (uri.contains("?")) {
             String[] splitURI = uri.split("\\?");
             return splitURI;
        }
        return uriWithNoParameters;
    }

    public void setHeaders(ArrayList<String> headers) {
        this.headers = headers;
    }
}
