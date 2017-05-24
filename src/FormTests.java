import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class FormTests {
    private Form form;

    @Before
    public void before() {
        Memory memory = new Memory("/Users/willcurry/cob_spec/public_test/");
        form = new Form(memory);
    }

    @Test
    public void formSavesDataWithPOST() throws IOException {
        form.withData(new FakeRequest("POST", "/form", "HTTP/1.1", "data=fatcat"));
        assertThat(Util.makeString(form.getResponse().asByteArray()), containsString("HTTP/1.1 200 OK"));
        form.withData(new FakeRequest("GET", "/form", "HTTP/1.1", ""));
        assertThat(Util.makeString(form.getResponse().asByteArray()), containsString("data=fatcat"));
    }

    @Test
    public void formSavesDataWithPUT() throws IOException {
        form.withData(new FakeRequest("PUT", "/form", "HTTP/1.1", "data=fatcat"));
        assertThat(Util.makeString(form.getResponse().asByteArray()), containsString("HTTP/1.1 200 OK"));
        form.withData(new FakeRequest("GET", "/form", "HTTP/1.1", ""));
        assertThat(Util.makeString(form.getResponse().asByteArray()), containsString("data=fatcat"));
    }

    @Test
    public void formRemovesDataWithDELETE() throws IOException {
        form.withData(new FakeRequest("PUT", "/form", "HTTP/1.1", "data=fatcat"));
        assertThat(Util.makeString(form.getResponse().asByteArray()), containsString("HTTP/1.1 200 OK"));
        form.withData(new FakeRequest("GET", "/form", "HTTP/1.1", ""));
        assertThat(Util.makeString(form.getResponse().asByteArray()), containsString("data=fatcat"));
        form.withData(new FakeRequest("DELETE", "/form", "HTTP/1.1", ""));
        assertFalse(Util.makeString(form.getResponse().asByteArray()).contains("data=fatcat"));
    }
}
