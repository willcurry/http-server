import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PatchContentTests {
    private FakeRequest fakeRequest;

    private void setUpFakeRequest(String verb, String uri, String body) {
        fakeRequest = new FakeRequest(verb, uri, "HTTP/1.1", body);
    }

    @Test
    public void hasContentOfDefaultContent() throws IOException {
        setUpFakeRequest("GET", "/patch-content.txt", "");
        PatchContent patchContent = new PatchContent();
        patchContent.withData(fakeRequest);
        assertThat(patchContent.getResponse().toString(), containsString("default content"));
    }

    @Test
    public void returns204NoContentAfterPatch() throws IOException {
        setUpFakeRequest("PATCH", "/patch-content.txt", "");
        PatchContent patchContent = new PatchContent();
        patchContent.withData(fakeRequest);
        assertThat(patchContent.getResponse().toString(), containsString("204"));
    }
}