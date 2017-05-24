import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MemoryTests {
    private Memory memory;

    @Before
    public void before() {
        memory = new Memory("/Users/willcurry/cob_spec/public_test/");
    }
    @Test
    public void canSaveAndGetData() {
        memory.saveData("hello");
        assertEquals(memory.getData(), "hello");
    }

    @Test
    public void canRemoveData() {
        memory.saveData("hello");
        assertEquals(memory.getData(), "hello");
        memory.removeData();
        assertEquals(memory.getData(), null);
    }

    @Test
    public void knowsIfItHasData() {
        memory.saveData("hello");
        assertEquals(memory.hasData(), true);
    }

    @Test
    public void canReadFile() throws IOException {
        assertThat(Util.makeString(memory.readFile("patch-content.txt")), is("default content"));
    }

    @Test
    public void canWriteToExistingFile() throws IOException {
        assertThat(memory.readFile("test-file.txt"), is("starting text".getBytes()));
        memory.writeToFile("test-file.txt", "test");
        assertThat(memory.readFile("test-file.txt"), is("test".getBytes()));
        memory.writeToFile("test-file.txt", "starting text");
    }

    @Test
    public void knowsIfAFileHasData() throws IOException {
        assertEquals(false, memory.fileHasData("empty.txt"));
        assertEquals(true , memory.fileHasData("test-file.txt"));
    }

    @Test
    public void canReadFileWithRange() throws IOException {
        assertThat(memory.readFileWithRange("test-file.txt", 4, 9), is("ting ".getBytes()));
    }
}
