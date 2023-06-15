package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EntryListTest {

    private EntryList e1;
    private Entry entry1;
    private Entry entry2;

    @BeforeEach
    public void setup() {
        e1 = new EntryList();
        entry1 = new Entry("abcd", 10);
        entry2 = new Entry("efgh", 0);
    }

    @Test
    public void addToListTest() {
        e1.addToList(entry1);
        assertEquals(entry1, e1.getElem(0));

    }

    @Test
    public void removeFromListTest() {
        e1.addToList(entry1);
        e1.addToList(entry2);
        e1.removeFromList(0);
        assertEquals(1, e1.size());
        assertEquals(entry2, e1.getElem(0));
        e1.removeFromList(0);
        assertEquals(0, e1.size());
    }


    @Test
    public void getLastElemTest() {
        e1.addToList(entry1);
        e1.addToList(entry2);

        assertEquals(entry2, e1.getLastElem());
    }

    @Test
    public void getElemTest1() {
        e1.addToList(entry1);
        e1.addToList(entry2);

        assertEquals(entry1, e1.getElem(0));
        assertEquals(entry2, e1.getElem(1));
    }

    @Test
    public void amountTest() {
        e1.addToList(entry1);
        e1.addToList(entry2);
        assertEquals(10, e1.amount());
    }


    @Test
    public void sizeTest() {
        assertEquals(0, e1.size());
        e1.addToList(entry1);
        assertEquals(1, e1.size());
        e1.addToList(entry2);
        assertEquals(2, e1.size());
    }

    @Test
    public void indexOfTest(){
        e1.addToList(entry1);
        e1.addToList(entry2);
        assertEquals(0, e1.indexOf(entry1));
        assertEquals(1, e1.indexOf(entry2));
    }

    @Test
    public void isEmptyTest(){
        boolean b = e1.isEmpty();
        assertEquals(true, b);
        e1.addToList(entry1);
        assertEquals(false, e1.isEmpty());
    }

    @Test
    public void getEntryListTest(){
        e1.addToList(entry1);
        e1.addToList(entry2);
        ArrayList<Entry> test = e1.getEntryList();
        assertEquals(entry1, test.get(0));
        assertEquals(entry2, test.get(1));
    }

}
