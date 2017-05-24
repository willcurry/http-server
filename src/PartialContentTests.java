import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class PartialContentTests {
    private FakeRequest fakeRequest;
    private PartialContent partialContent;

    private void setUpFakeRequest(String verb, String uri, String body) {
        fakeRequest = new FakeRequest(verb, uri, "HTTP/1.1", body);
    }

    @Before
    public void before() {
        Memory memory = new Memory("/Users/willcurry/cob_spec/public_test/");
        partialContent = new PartialContent(memory);
    }

    @Test
    public void responseCodeIs206() throws IOException {
        setUpFakeRequest("GET", "/patch-content.txt", "");
        partialContent.withData(fakeRequest);
        assertThat(partialContent.getResponse().toString(), containsString("206"));
    }
}
