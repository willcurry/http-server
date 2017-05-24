import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MemoryTests {
    @Test
    public void canSaveAndGetData() {
        Memory memory = new Memory();
        memory.saveData("/form", "hello");
        assertEquals(memory.dataForURI("/form"), "hello");
    }
}
