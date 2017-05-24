import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

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
        assertEquals(memory.readFile("patch-content.txt"), "default content");
    }

    @Test
    public void canWriteToExistingFile() throws IOException {
        assertEquals(memory.readFile("test-file.txt"), "starting text");
        memory.writeToFile("test-file.txt", "test");
        assertEquals(memory.readFile("test-file.txt"), "test");
        memory.writeToFile("test-file.txt", "starting text");
    }

    @Test
    public void knowsIfAFileHasData() throws IOException {
        assertEquals(false, memory.fileHasData("empty.txt"));
        assertEquals(true , memory.fileHasData("test-file.txt"));
    }
}
