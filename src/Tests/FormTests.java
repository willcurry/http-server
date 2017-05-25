package Tests;

import Routes.FormRoute;
import Server.Memory;
import Server.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class FormTests {
    private FormRoute form;

    @Before
    public void before() {
        Memory memory = new Memory("/Users/willcurry/cob_spec/public_test/");
        form = new FormRoute(memory);
    }

    @Test
    public void formSavesDataWithPOST() throws IOException {
        Response response = form.handlePOST(new FakeRequest("POST", "/form", "HTTP/1.1", "data=fatcat"));
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("HTTP/1.1 200 OK"));
        response = form.handleGET(new FakeRequest("GET", "/form", "HTTP/1.1", ""));
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("data=fatcat"));
    }

    @Test
    public void formSavesDataWithPUT() throws IOException {
        Response response = form.handlePUT(new FakeRequest("PUT", "/form", "HTTP/1.1", "data=fatcat"));
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("HTTP/1.1 200 OK"));
        response = form.handleGET(new FakeRequest("GET", "/form", "HTTP/1.1", ""));
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("data=fatcat"));
    }

    @Test
    public void formRemovesDataWithDELETE() throws IOException {
        Response response = form.handlePUT(new FakeRequest("PUT", "/form", "HTTP/1.1", "data=fatcat"));
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("HTTP/1.1 200 OK"));
        response = form.handleGET(new FakeRequest("GET", "/form", "HTTP/1.1", ""));
        assertThat(TestUtil.makeString(response.asByteArray()), containsString("data=fatcat"));
        response = form.handleDELETE(new FakeRequest("DELETE", "/form", "HTTP/1.1", ""));
        assertFalse(TestUtil.makeString(response.asByteArray()).contains("data=fatcat"));
    }
}
