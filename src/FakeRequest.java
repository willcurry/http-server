import java.io.IOException;
import java.util.ArrayList;

public class FakeRequest implements HTTPRequest {
    private final String uri;

    public FakeRequest(String uri) {
        this.uri = uri;
    }

    @Override
    public String getVerb() throws IOException {
        return null;
    }

    @Override
    public String getURI() throws IOException {
        return uri;
    }

    @Override
    public String getHTTPVersion() throws IOException {
        return null;
    }

    @Override
    public ArrayList<String> getHeaders() throws IOException {
        return null;
    }

    @Override
    public String getBody() throws IOException {
        return null;
    }
}
