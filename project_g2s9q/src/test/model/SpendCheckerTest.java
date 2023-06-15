package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SpendCheckerTest {

    private SpendChecker sc;

    private EntryList e;
    private Entry e1;
    private Entry e2;
    private Entry e3;


    @BeforeEach
    public void setup() {
        e1 = new Entry("", 100);
        e2 = new Entry("abcd", 500);
        e3 = new Entry("jklm", 900);
        e3 = new Entry("efgh", 1000);
        sc = new SpendChecker();

    }

    @Test
    public void spendCheckerTest() {
        assertEquals(0, sc.getBalance());
        assertEquals(0, sc.getDeposit());
        assertEquals(0, sc.getLimit());
    }

    @Test
    public void spendCheckerWithParametersTest() {
        SpendChecker sc = new SpendChecker(50, 25);
        assertEquals(50, sc.getDeposit());
        assertEquals(25, sc.getLimit());
    }

    @Test
    public void setLimitLessTest() {
        sc.setDeposit(100);
        sc.setLimit(10);
        assertEquals(10, sc.getLimit());
    }

    @Test
    public void setLimitMoreTest() {
        sc.setDeposit(100);
        sc.setLimit(110);
        assertEquals(0, sc.getLimit());
    }

    @Test
    public void setDepositTest() {
        sc.setDeposit(100);
        assertEquals(100, sc.getDeposit());
        sc.setDeposit(50);
        assertEquals(50, sc.getDeposit());
    }

    @Test
    public void getLimitTest() {
        sc.setDeposit(100);
        sc.setLimit(50);
        assertEquals(50, sc.getLimit());
        sc.setLimit(60);
        assertEquals(60, sc.getLimit());
    }

    @Test
    public void getDepositTest() {
        sc.setDeposit(100);
        assertEquals(100, sc.getDeposit());
        sc.setDeposit(50);
        assertEquals(50, sc.getDeposit());

    }


    @Test
    public void addToListEntryAmountLessThanDepositTest() {

        sc.setDeposit(1000);
        sc.setLimit(600);

        sc.addToList(e1);
        boolean b = sc.addToList(e2);
        assertTrue(b);

    }

    @Test
    public void addToListEntryAmountEqualToDepositTest() {

        sc.setDeposit(600);
        sc.setLimit(600);

        sc.addToList(e1);
        boolean b = sc.addToList(e2);
        assertTrue(b);
    }

    @Test
    public void addToListEntryAmountMoreThanDepositTest() {

        sc.setDeposit(200);
        sc.setLimit(100);

        sc.addToList(e1);
        boolean b = sc.addToList(e2);
        assertFalse(b);

    }

    @Test
    public void addToListEntryAmountLessThanLimitTest() {

        sc.setDeposit(800);
        sc.setLimit(601);

        sc.addToList(e1);
        boolean b = sc.addToList(e2);
        assertTrue(b);
        assertEquals(false, sc.getLimitWarningBoolean());

    }

    @Test
    public void addToListEntryAmountMoreThanLimitTest() {

        sc.setDeposit(600);
        sc.setLimit(400);

        sc.addToList(e1);
        boolean b = sc.addToList(e2);
        assertEquals(true, sc.getLimitWarningBoolean());

    }

    @Test
    public void addToListEntryAmountEqualToLimitTest() {

        sc.setDeposit(600);
        sc.setLimit(600);

        sc.addToList(e1);
        sc.addToList(e2);
        assertEquals(true, sc.getLimitWarningBoolean());

    }


    @Test
    public void addToListEntryByStringAmountLessThanDepositTest() {

        sc.setDeposit(1000);
        sc.setLimit(600);

        sc.addToList("", 100);
        boolean b = sc.addToList("abcd", 500);
        assertTrue(b);

    }

    @Test
    public void addToListEntryByStringAmountEqualToDepositTest() {

        sc.setDeposit(600);
        sc.setLimit(600);

        sc.addToList("", 100);
        boolean b = sc.addToList("abcd", 500);
        assertTrue(b);
    }

    @Test
    public void addToListEntryByStringAmountMoreThanDepositTest() {

        sc.setDeposit(200);
        sc.setLimit(100);

        sc.addToList("", 100);
        boolean b = sc.addToList("abcd", 500);
        assertFalse(b);

    }

    @Test
    public void addToListEntryByStringAmountLessThanLimitTest() {

        sc.setDeposit(800);
        sc.setLimit(601);

        sc.addToList("", 100);
        boolean b = sc.addToList("abcd", 500);
        assertTrue(b);
        assertEquals(false, sc.getLimitWarningBoolean());

    }

    @Test
    public void addToListEntryByStringAmountMoreThanLimitTest() {

        sc.setDeposit(600);
        sc.setLimit(400);

        sc.addToList("", 100);
        boolean b = sc.addToList("abcd", 500);
        assertEquals(true, sc.getLimitWarningBoolean());

    }

    @Test
    public void addToListEntryByStringAmountEqualToLimitTest() {

        sc.setDeposit(600);
        sc.setLimit(600);

        sc.addToList("", 100);
        boolean b = sc.addToList("abcd", 500);
        assertEquals(true, sc.getLimitWarningBoolean());

    }


    @Test
    public void getBalanceTest() {
        sc.setDeposit(800);
        sc.addToList(e1);
        sc.addToList(e2);
        assertEquals(200, sc.getBalance());
    }


    @Test
    public void deleteEntryTest() {
        sc.setDeposit(1000);
        sc.setLimit(800);
        sc.addToList(e1);
        sc.addToList(e2);
        sc.deleteEntry(1);
        assertEquals(500, sc.getBalance());
        EntryList el = sc.getEntryList();
        assertEquals(1, el.size());
        assertEquals(e2, el.getElem(0));

    }

    @Test
    public void printAccountSummaryTest() {
        sc.setDeposit(1500);
        sc.setLimit(1000);
        sc.addToList(e1);
        sc.addToList(e2);
        sc.addToList(e3);
        assertEquals("Deposit amount is: " + 1500 + " Limit of the account is currently set at: " + 1000
                + " Your current balance is: " + 900 , sc.printAccountSummary());

    }

    @Test
    public void setEntryListTest() {
        EntryList test = new EntryList();
        test.addToList(e1);
        test.addToList(e2);
        test.addToList(e3);
        sc.setEntryList(test);
        EntryList test2 = sc.getEntryList();
        assertEquals(test, test2);
    }

}


