package Tests;

import Routes.CoffeeRoute;
import Routes.DefaultPageRoute;
import Routes.PublicFilesRoute;
import Routes.TeaRoute;
import Server.Storage;
import Server.Parameters;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class SimpleRouteTests {
    private TeaRoute tea;
    private PublicFilesRoute files;
    private CoffeeRoute coffee;
    private Parameters parameters;
    private Storage storage;

    @Before
    public void before() {
        storage = new Storage("/Users/willcurry/cob_spec/public_test/");
        files = new PublicFilesRoute(storage);
        tea = new TeaRoute();
        coffee = new CoffeeRoute();
        parameters = new Parameters();
    }

    @Test
    public void getOnTextFileReturns200Response() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/text-file.txt", "");
        assertThat(TestUtil.makeString(files.handleGET(fakeRequest).asByteArray()), is("HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: 14\n\nfile1 contents"));
    }

    @Test
    public void postOnTextFileReturns405Response() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("POST", "/text-file.txt", "");
        assertThat(TestUtil.makeString(files.handlePOST(fakeRequest).asByteArray()), is("HTTP/1.1 405 Method Not Allowed"));
    }

    @Test
    public void getOnFile1Returns200Response() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/file1", "");
        assertThat(TestUtil.makeString(files.handleGET(fakeRequest).asByteArray()), is("HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: 14\n\nfile1 contents"));
    }

    @Test
    public void postOnFile1Returns405Response() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("POST", "/files", "");
        assertThat(TestUtil.makeString(files.handlePOST(fakeRequest).asByteArray()), is("HTTP/1.1 405 Method Not Allowed"));
    }

    @Test
    public void getOnTeaReturns200Response() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/tea", "");
        assertThat(TestUtil.makeString(tea.handleGET(fakeRequest).asByteArray()), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void getOnCoffeeReturns418ResponseAndContentIsImATeaPot() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/coffee", "");
        assertThat(TestUtil.makeString(coffee.handleGET(fakeRequest).asByteArray()), is("HTTP/1.1 418 Teapot\nContent-Type: text/plain\nContent-Length: 12\n\nI'm a teapot"));
    }

    @Test
    public void getOnParametersReturnsDecodedParametersInTheBody() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff", "");
        String body = "variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?" + "variable_2 = stuff";
        assertThat(TestUtil.makeString(parameters.handleGET(fakeRequest).asByteArray()), is("HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: " + body.length() + "\n\n" + body));
    }

    @Test
    public void getOnDefaultPageReturnsAllFiles() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/", "");
        DefaultPageRoute defaultPageRoute = new DefaultPageRoute(storage);
        assertThat(TestUtil.makeString(defaultPageRoute.handleGET(fakeRequest).asByteArray()), containsString("\n\ntext-file.txt"));
    }
}
