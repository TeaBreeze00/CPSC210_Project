package persistance;

import model.Entry;
import model.EntryList;
import model.SpendChecker;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SpendChecker sc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //the test pass
        }
    }

    @Test
    void testReaderEmptySpendChecker() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySpendChecker.json");
        try {
            SpendChecker sc = reader.read();
            assertEquals(5, sc.getDeposit());
            assertEquals(5, sc.getLimit());
            assertEquals(0, sc.getEntryListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSpendChecker.json");
        try {
            SpendChecker sc = reader.read();
            assertEquals(20, sc.getDeposit());
            assertEquals(5, sc.getLimit());
            EntryList el = sc.getEntryList();
            assertEquals(2, el.size());
            Entry e1 = el.getElem(0);
            Entry e2 = el.getElem(1);
            assertEquals(e1.getAmount(), 10);
            assertEquals(e2.getAmount(), 5);
            assertEquals(e1.getCategory(), "cat");
            assertEquals(e2.getCategory(), "bat");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

