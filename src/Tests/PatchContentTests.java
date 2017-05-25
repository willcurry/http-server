package Tests;

import Routes.PatchContent;
import Server.Memory;
import Server.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class PatchContentTests {
    private PatchContent patchContent;

    @Before
    public void before() {
        Memory memory = new Memory("/Users/willcurry/cob_spec/public_test/");
        patchContent = new PatchContent(memory);
    }

    @Test
    public void hasContentOfDefaultContent() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/patch-content.txt", "");
        Response response = patchContent.handleGET(fakeRequest);
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("default content"));
    }

    @Test
    public void returns204NoContentAfterPatch() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("PATCH", "/patch-content.txt", "default content");
        Response response = patchContent.handlePATCH(fakeRequest);
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("204"));
    }

    @Test
    public void patchGetPatchGet() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("PATCH", "/patch-content.txt", "hello");
        Response response = patchContent.handlePATCH(fakeRequest);
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("204"));
        fakeRequest = TestUtil.createFakeRequest("GET", "/patch-content.txt", "");
        response = patchContent.handleGET(fakeRequest);
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("hello"));
        fakeRequest = TestUtil.createFakeRequest("PATCH", "/patch-content.txt", "default content");
        response = patchContent.handlePATCH(fakeRequest);
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("204"));
        fakeRequest = TestUtil.createFakeRequest("GET", "/patch-content.txt", "");
        response = patchContent.handleGET(fakeRequest);
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("default content"));
    }
}
