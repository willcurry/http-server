package Tests.RouteTests;

import Server.Routes.PatchContentRoute;
import Server.Response;
import Server.Storage;
import Tests.FakeRequest;
import Tests.TestUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class PatchContentRouteTests {
    private PatchContentRoute patchContent;

    @Before
    public void before() {
        Storage storage = new Storage("src/public_test/");
        patchContent = new PatchContentRoute(storage);
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
