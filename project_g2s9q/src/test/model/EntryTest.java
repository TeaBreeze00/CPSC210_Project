package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntryTest {
    private Entry e1;
    private Entry e2;

    @BeforeEach
    public void setup() {
        e1 = new Entry("", 0);
        e2 = new Entry("Nothing", 100);
    }

    @Test
    public void entryTest() {
        assertEquals("", e1.getCategory());
        assertEquals(0, e1.getAmount());
        assertEquals("Nothing", e2.getCategory());
        assertEquals(100, e2.getAmount());
    }

    @Test
    public void setAmountTest() {
        e1.setAmount(100);
        assertEquals(100, e1.getAmount());
    }

    @Test
    public void setCategory() {
        e1.setCategory("abcd");
        e2.setCategory("efgh");
        assertEquals("abcd", e1.getCategory());
        assertEquals("efgh", e2.getCategory());
    }

    @Test
    public void getAmountTest() {
        assertEquals(0, e1.getAmount());
        assertEquals(100, e2.getAmount());
    }

    @Test
    public void getCategoryTest() {
        assertEquals("", e1.getCategory());
        assertEquals("Nothing", e2.getCategory());
    }

    @Test
    public void printEntrySummaryTest() {
        assertEquals("The amount selected is currently: " + 0 + " for the category: " + "", e1.printEntrySummary());
        assertEquals("The amount selected is currently: " + 100 + " for the category: " + "Nothing", e2.printEntrySummary());
    }

}