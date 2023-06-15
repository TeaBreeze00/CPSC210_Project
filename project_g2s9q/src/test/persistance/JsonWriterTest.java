package persistance;

import model.Entry;
import model.EntryList;
import model.SpendChecker;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            SpendChecker wr = new SpendChecker(5, 5);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySpendChecker() {
        try {
            SpendChecker sc = new SpendChecker(5, 5);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySpendChecker.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySpendChecker.json");
            sc = reader.read();
            assertEquals(5, sc.getDeposit());
            assertEquals(5, sc.getLimit());
            assertEquals(0, sc.getEntryListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            SpendChecker sc = new SpendChecker(20, 5);
            sc.addToList(new Entry("cat", 10));
            sc.addToList(new Entry("bat", 5));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSpendChecker.json");
            writer.open();
            writer.write(sc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSpendChecker.json");
            sc = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
