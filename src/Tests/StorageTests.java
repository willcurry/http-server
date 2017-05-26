package Tests;

import Server.Storage;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StorageTests {
    private Storage storage;

    @Before
    public void before() {
        storage = new Storage("/Users/willcurry/cob_spec/public_test/");
    }
    @Test
    public void canSaveAndGetData() {
        storage.saveData("hello");
        assertEquals(storage.getData(), "hello");
    }

    @Test
    public void canRemoveData() {
        storage.saveData("hello");
        assertEquals(storage.getData(), "hello");
        storage.removeData();
        assertEquals(storage.getData(), null);
    }

    @Test
    public void knowsIfItHasData() {
        storage.saveData("hello");
        assertEquals(storage.hasData(), true);
    }

    @Test
    public void canReadFile() throws IOException {
        assertThat(TestUtil.makeString(storage.readFile("patch-content.txt")), is("default content"));
    }

    @Test
    public void canWriteToExistingFile() throws IOException {
        assertThat(storage.readFile("test-file.txt"), is("starting text".getBytes()));
        storage.writeToFile("test-file.txt", "test");
        assertThat(storage.readFile("test-file.txt"), is("test".getBytes()));
        storage.writeToFile("test-file.txt", "starting text");
    }

    @Test
    public void knowsIfAFileHasData() throws IOException {
        assertEquals(false, storage.fileHasData("empty.txt"));
        assertEquals(true , storage.fileHasData("test-file.txt"));
    }

    @Test
    public void canReadFileWithRange() throws IOException {
        assertThat(storage.readFileWithRange("test-file.txt", 4, 9), is("ting ".getBytes()));
    }

    @Test
    public void knowsFilesInADirectory() throws IOException {
        assertThat(storage.allFiles().toString(), is("[/Users/willcurry/cob_spec/public_test, /Users/willcurry/cob_spec/public_test/.DS_Store, /Users/willcurry/cob_spec/public_test/empty.txt, /Users/willcurry/cob_spec/public_test/file1, /Users/willcurry/cob_spec/public_test/file2, /Users/willcurry/cob_spec/public_test/image.gif, /Users/willcurry/cob_spec/public_test/image.jpeg, /Users/willcurry/cob_spec/public_test/image.png, /Users/willcurry/cob_spec/public_test/partial_content.txt, /Users/willcurry/cob_spec/public_test/patch-content.txt, /Users/willcurry/cob_spec/public_test/test-file.txt, /Users/willcurry/cob_spec/public_test/text-file.txt]"));
    }

    @Test
    public void canReadAnImageFile() throws IOException {
        assertThat(storage.readFile("image.jpeg"), is(Files.readAllBytes(Paths.get("/Users/willcurry/cob_spec/public_test/image.jpeg"))));
    }

    @Test
    public void knowsAllFileNames() throws IOException {
        assertTrue(storage.allFileNames().contains("file1"));
        assertTrue(storage.allFileNames().contains("text-file.txt"));
        assertTrue(storage.allFileNames().contains("empty.txt"));
        assertFalse(storage.allFileNames().contains("fake file !!!"));
    }
}
