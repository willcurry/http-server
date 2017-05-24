import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class PatchContentTests {
    private FakeRequest fakeRequest;
    private PatchContent patchContent;

    private void setUpFakeRequest(String verb, String uri, String body) {
        fakeRequest = new FakeRequest(verb, uri, "HTTP/1.1", body);
    }

    @Before
    public void before() {
        Memory memory = new Memory("/Users/willcurry/cob_spec/public_test/");
        patchContent = new PatchContent(memory);
    }

    @Test
    public void hasContentOfDefaultContent() throws IOException {
        setUpFakeRequest("GET", "/patch-content.txt", "");
        patchContent.withData(fakeRequest);
        assertThat(patchContent.getResponse().toString(), containsString("default content"));
    }

    @Test
    public void returns204NoContentAfterPatch() throws IOException {
        setUpFakeRequest("PATCH", "/patch-content.txt", "default content");
        patchContent.withData(fakeRequest);
        assertThat(patchContent.getResponse().toString(), containsString("204"));
    }

    @Test
    public void patchGetPatchGet() throws IOException {
        setUpFakeRequest("PATCH", "/patch-content.txt", "hello");
        patchContent.withData(fakeRequest);
        assertThat(patchContent.getResponse().toString(), containsString("204"));
        setUpFakeRequest("GET", "/patch-content.txt", "");
        patchContent.withData(fakeRequest);
        assertThat(patchContent.getResponse().toString(), containsString("hello"));
        setUpFakeRequest("PATCH", "/patch-content.txt", "default content");
        patchContent.withData(fakeRequest);
        assertThat(patchContent.getResponse().toString(), containsString("204"));
        setUpFakeRequest("GET", "/patch-content.txt", "");
        patchContent.withData(fakeRequest);
        assertThat(patchContent.getResponse().toString(), containsString("default content"));
    }
}
