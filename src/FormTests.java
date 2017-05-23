import org.junit.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class FormTests {
    @Test
    public void formKeepsData() {
        Form form = new Form();
        form.withData(new FakeRequest("POST", "/form", "HTTP/1.1", "data=fatcat"));
        assertEquals(form.getResponse().toString(), "HTTP/1.1 200 OK");
        form.withData(new FakeRequest("GET", "/form", "HTTP/1.1", ""));
        assertThat(form.getResponse().toString(), containsString("data=fatcat"));
    }
}
