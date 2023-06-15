package model;

//import org.json.JSONObject;

import org.json.JSONObject;

//Reprsents a SpendChecker App with added functionalities like deposit,
// setting account limit and also printing account information

public class SpendChecker {


    private int deposit;
    private int limit;

    private int balance;
    private EntryList entryList;

    private boolean limitWarningBoolean;


    //Effects: creates a new SpendChecker object with default deposit and limit values
    public SpendChecker() {
        entryList = new EntryList();
    }

    public SpendChecker(int deposit, int limit) {
        this.deposit = deposit;
        this.limit = limit;
        entryList = new EntryList();
    }

    public void setEntryList(EntryList entryList) {
        this.entryList = entryList;
    }

    //Modifies: this
    //Effects: sets the limit of SpendChecker object by the specified amount,
    // if the limit exceeds the deposit amount, it prints out a message and doesn't set the limit
    public boolean setLimit(int limit) {
        if (limit <= this.getDeposit()) {
            this.limit = limit;
            return true;
        }
        return false;
    }

    public EntryList getEntryList() {
        return entryList;
    }

    //REQ: deposit > 0
    //Modifies: this
    //Effects: sets the initial deposit by the specified amount
    public boolean setDeposit(int deposit) {

        this.deposit = deposit;

        return true;
    }

    //Effects: returns the limit of the object
    public int getLimit() {
        return this.limit;
    }

    //Effects: returns the deposit amount
    public int getDeposit() {
        return deposit;
    }

    //Effects: if sum of entries are less or equal than the depositted amount
    //                 if sum of entries are greater than or equal to the limit
    //                          - Display a warning message
    //                - adds the entry to the list
    //         else displays it cannot add new elements to the list
    public boolean addToList(String cat, int amt) {
        Entry entry = new Entry(cat, amt);
        if (entryList.amount() + entry.getAmount() <= this.getDeposit()) {
            if (entryList.amount() + entry.getAmount() >= this.getLimit()) {
                this.limitWarningBoolean = true;
            } else {
                this.limitWarningBoolean = false;
            }
            entryList.addToList(entry);
            return true;
        } else {
            return false;
        }
    }

    //Effects: if sum of entries are less or equal than the depositted amount
    //                 if sum of entries are greater than or equal to the limit
    //                          - Display a warning message
    //                - adds the entry to the list
    //         else displays it cannot add new elements to the list
    public boolean addToList(Entry entry) {
        if (entryList.amount() + entry.getAmount() <= this.getDeposit()) {
            if (entryList.amount() + entry.getAmount() >= this.getLimit()) {
                this.limitWarningBoolean = true;
            } else {
                this.limitWarningBoolean = false;
            }
            entryList.addToList(entry);
            return true;
        } else {
            return false;
        }
    }

    //Effects: returns the value of the limitWarningBoolean
    public boolean getLimitWarningBoolean() {
        return this.limitWarningBoolean;
    }

    //Effects: returns the difference between the deposit amount and all entry amount of the list
    public int getBalance() {
        return this.deposit - entryList.amount();
    }

    //Modifies: this
    //Effects: Deletes a specific entry based on the id
    public boolean deleteEntry(int id) {
        this.balance += entryList.getElem(id - 1).getAmount();
        entryList.removeFromList(id - 1);
        return true;
    }

    //Effects: returns the size of entryList
    public int getEntryListSize() {
        return entryList.size();
    }

    //Effects: Prints out the deposit amount, limit amount and balance of the current object
    public String printAccountSummary() {
        return "Deposit amount is: " + getDeposit() + " Limit of the account is currently set at: " + getLimit()
                + " Your current balance is: " + this.getBalance();
    }

    //Effects: Returns SpendChecker model as a jsonArray
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Deposit", deposit);
        json.put("Limit", limit);
        json.put("List of Entries", entryList.toJson());
        return json;
    }


}
