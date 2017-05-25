package Tests;

import Routes.Coffee;
import Routes.File1;
import Routes.Tea;
import Routes.TextFile;
import Server.Memory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleRouteTests {
    private TextFile textFile;
    private File1 file1;
    private Tea tea;
    private Coffee coffee;

    @Before
    public void before() {
        textFile = new TextFile();
        Memory memory = new Memory("/Users/willcurry/cob_spec/public_test/");
        file1 = new File1(memory);
        tea = new Tea();
        coffee = new Coffee();
    }

    @Test
    public void getOnTextFileReturns200Response() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/text-file.txt", "");
        assertThat(TestUtil.makeString(textFile.handleGET(fakeRequest).asByteArray()), is("HTTP/1.1 200 OK"));
    }

    @Test
    public void postOnTextFileReturns405Response() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("POST", "/text-file.txt", "");
        assertThat(TestUtil.makeString(textFile.handlePOST(fakeRequest).asByteArray()), is("HTTP/1.1 405 Method Not Allowed"));
    }

    @Test
    public void getOnFile1Returns200Response() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("GET", "/file1", "");
        assertThat(TestUtil.makeString(file1.handleGET(fakeRequest).asByteArray()), is("HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: 14\n\nfile1 contents"));
    }

    @Test
    public void postOnFile1Returns405Response() throws IOException {
        FakeRequest fakeRequest = TestUtil.createFakeRequest("POST", "/file1", "");
        assertThat(TestUtil.makeString(file1.handlePOST(fakeRequest).asByteArray()), is("HTTP/1.1 405 Method Not Allowed"));
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
        assertThat(TestUtil.makeString(coffee.handleGET(fakeRequest).asByteArray()), is("HTTP/1.1 200 OK\nContent-Type: text/plain\nContent-Length: " + body.length() + "\n\n" + body));
    }
}
