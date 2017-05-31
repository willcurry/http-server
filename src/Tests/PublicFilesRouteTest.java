package Tests;

import Server.Routes.PublicFilesRoute;
import Server.Storage;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PublicFilesRouteTest  {
    private Storage storage;
    private PublicFilesRoute publicFilesRoute;

    @Before
    public void before() {
        storage = new Storage("src/public_test/");
        publicFilesRoute = new PublicFilesRoute(storage);
    }

    @Test
    public void itAppliesToFilesInThePublicFolder() throws IOException {
        assertTrue(publicFilesRoute.appliesTo("/text-file.txt"));
        assertTrue(publicFilesRoute.appliesTo("/test-file.txt"));
        assertTrue(publicFilesRoute.appliesTo("/file1"));
        assertFalse(publicFilesRoute.appliesTo("/invalid-file"));
    }

    @Test
    public void handleGETReturnsContentOfATextFile() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/text-file.txt", "");
        assertThat(publicFilesRoute.handleGET(fakeRequest).asByteArray(),
                is("HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: 14\n\nfile1 contents".getBytes()));
    }

    @Test
    public void knowsContentType() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/text-file.txt", "");
        assertThat(publicFilesRoute.handleGET(fakeRequest).asByteArray(),
                is("HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: 14\n\nfile1 contents".getBytes()));
    }

    @Test
    public void handleGETReturnsContentOfAJPEGFile() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/image.jpeg", "");
        ByteArrayOutputStream expected = new ByteArrayOutputStream();
        byte[] imageBytes = storage.readFile("image.jpeg");
        String expectedResponse = "HTTP/1.1 200 OK\nContent-Type: image/jpeg\nContent-Length: " + imageBytes.length + "\n\n";
        expected.write(expectedResponse.getBytes());
        expected.write(imageBytes);
        assertThat(publicFilesRoute.handleGET(fakeRequest).asByteArray(), is(expected.toByteArray()));
    }

    @Test
    public void knowsTheContentType() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/image.jpeg", "");
        assertThat(TestUtil.makeString(publicFilesRoute.handleGET(fakeRequest).asByteArray()), containsString("Content-Type: image/jpeg"));
    }
}
