import java.io.IOException;
import java.util.ArrayList;

public class FakeRequest implements HTTPRequest {
    private final String uri;
    private final String body;
    private final String httpVersion;
    private final String verb;

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
        return uri;
    }

    @Override
    public String getHTTPVersion() throws IOException {
        return this.httpVersion;
    }

    @Override
    public ArrayList<String> getHeaders() throws IOException {
        return null;
    }

    @Override
    public String getBody() throws IOException {
        return this.body;
    }
}
