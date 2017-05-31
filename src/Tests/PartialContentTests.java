package Tests;

import Routes.PartialContentRoute;
import Server.Response;
import Server.Storage;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class PartialContentTests {
    private PartialContentRoute partialContent;
    private ArrayList headers;

    @Before
    public void before() {
        headers = new ArrayList<>();
        Storage storage = new Storage("/Users/willcurry/cob_spec/public_test/");
        partialContent = new PartialContentRoute(storage);
    }

    @Test
    public void responseCodeIs206() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/partial_content.txt", "");
        fakeRequest.setHeaders(headers);
        String headersString = "Range: bytes=0-4\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate";
        for (String header : headersString.split("\n")) headers.add(header);
        Response response = partialContent.handleGET(fakeRequest);
        assertThat(TestUtil.makeString(response.asByteArray()), is("HTTP/1.1 206 OK\nContent-Type: text/plain\nContent-Length: 5\n\nThis "));
    }

    @Test
    public void canFindRange() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/partial_content.txt", "");
        fakeRequest.setHeaders(headers);
        String headersString = "Range: bytes=0-4\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate";
        for (String header : headersString.split("\n")) headers.add(header);
        Response response = partialContent.handleGET(fakeRequest);
        assertThat(TestUtil.makeString(response.asByteArray()), is("HTTP/1.1 206 OK\nContent-Type: text/plain\nContent-Length: 5\n\nThis "));
    }

    @Test
    public void canFindRangeWhenRangeEndIsEmpty() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/partial_content.txt", "");
        fakeRequest.setHeaders(headers);
        String headersString = "Range: bytes=4-\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate";
        for (String header : headersString.split("\n")) headers.add(header);
        Response response = partialContent.handleGET(fakeRequest);
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("\n\n is a file that contains text to read part of in order to fulfill a 206.\n"));
    }

    @Test
    public void canFindRangeWhenRangeStartIsEmpty() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/partial_content.txt", "");
        fakeRequest.setHeaders(headers);
        String headersString = "Range: bytes=-6\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate";
        for (String header : headersString.split("\n")) headers.add(header);
        Response response = partialContent.handleGET(fakeRequest);
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("\n\n 206.\n"));
    }
}
