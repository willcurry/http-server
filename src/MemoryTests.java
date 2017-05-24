import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MemoryTests {
    @Test
    public void canSaveAndGetData() {
        Memory memory = new Memory();
        memory.saveData("hello");
        assertEquals(memory.getData(), "hello");
    }

    @Test
    public void canRemoveData() {
        Memory memory = new Memory();
        memory.saveData("hello");
        assertEquals(memory.getData(), "hello");
        memory.removeData();
        assertEquals(memory.getData(), null);
    }

    @Test
    public void knowsIfItHasData() {
        Memory memory = new Memory();
        memory.saveData("hello");
        assertEquals(memory.hasData(), true);
    }

    @Test
    public void canReadFile() throws IOException {
        Memory memory = new Memory();
        assertEquals(memory.readFile("/Users/willcurry/cob_spec/public/patch-content.txt"), "default content");
    }

    @Test
    public void canWriteToExistingFile() throws IOException {
        Memory memory = new Memory();
        assertEquals(memory.readFile("/Users/willcurry/cob_spec/public/test-file.txt"), "starting text");
        memory.writeToFile("/Users/willcurry/cob_spec/public/test-file.txt", "test");
        assertEquals(memory.readFile("/Users/willcurry/cob_spec/public/test-file.txt"), "test");
        memory.writeToFile("/Users/willcurry/cob_spec/public/test-file.txt", "starting text");
    }

    @Test
    public void knowsIfAFileHasData() throws IOException {
        Memory memory = new Memory();
        assertEquals(false, memory.fileHasData("/Users/willcurry/cob_spec/public/empty.txt"));
        assertEquals(true , memory.fileHasData("/Users/willcurry/cob_spec/public/test-file.txt"));
    }
}
