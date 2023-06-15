package model;

import org.json.JSONObject;

//Represents an entry object with a specific category and amount
public class Entry {

    private int amount;
    private String category;

    //Effects: Creates a new Entry object with amount and category fields
    public Entry(String category, int amount) {
        setAmount(amount);
        setCategory(category);
    }

    //Req: amount>0
    //Modifies: this
    //Effects: sets the amount of an entry object to a specific value
    public void setAmount(int amount) {
        this.amount = amount;
    }

    //Modifies: this
    //Effects: sets the name of the entry object to the specific name
    public void setCategory(String category) {
        this.category = category;
    }

    //Modifies: this
    //Effects: returns the amount of the entry object
    public int getAmount() {
        return this.amount;
    }

    //Modifies: this
    //Effects: returns the category of the entry object
    public String getCategory() {
        return this.category;
    }

    //Effects: Prints the stepwise summary of each entry
    public String printEntrySummary() {
        return "The amount selected is currently: " + this.getAmount() + " for the category: " + this.getCategory();
    }

    //Effects: returns each entry as a jsonobject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amount", amount);
        json.put("category", category);
        return json;
    }

}

