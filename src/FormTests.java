import org.junit.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class FormTests {
    @Test
    public void formSavesDataWithPOST() {
        Form form = new Form();
        form.withData(new FakeRequest("POST", "/form", "HTTP/1.1", "data=fatcat"));
        assertEquals(form.getResponse().toString(), "HTTP/1.1 200 OK");
        form.withData(new FakeRequest("GET", "/form", "HTTP/1.1", ""));
        assertThat(form.getResponse().toString(), containsString("data=fatcat"));
    }

    @Test
    public void formSavesDataWithPUT() {
        Form form = new Form();
        form.withData(new FakeRequest("PUT", "/form", "HTTP/1.1", "data=fatcat"));
        assertEquals(form.getResponse().toString(), "HTTP/1.1 200 OK");
        form.withData(new FakeRequest("GET", "/form", "HTTP/1.1", ""));
        assertThat(form.getResponse().toString(), containsString("data=fatcat"));
    }

    @Test
    public void formRemovesDataWithDELETE() {
        Form form = new Form();
        form.withData(new FakeRequest("PUT", "/form", "HTTP/1.1", "data=fatcat"));
        assertEquals(form.getResponse().toString(), "HTTP/1.1 200 OK");
        form.withData(new FakeRequest("GET", "/form", "HTTP/1.1", ""));
        assertThat(form.getResponse().toString(), containsString("data=fatcat"));
        form.withData(new FakeRequest("DELETE", "/form", "HTTP/1.1", ""));
        assertFalse(form.getResponse().toString().contains("data=fatcat"));
    }
}
