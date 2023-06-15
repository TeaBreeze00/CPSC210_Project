package model;

//import org.json.JSONArray;
//import org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents an entryList object with a list of specific entries
public class EntryList {
    private ArrayList<Entry> entryList = new ArrayList<>();

    //Modifies: This
    //Effects: adds a new Entry object to the list at the last index
    public void addToList(Entry e) {
        entryList.add(e);
    }

    //Modifies: This
    //Effects:  removes a specific entry object from the list by its index
    public void removeFromList(int id) {
        entryList.remove(id);
    }


    //Effects: returns the last Entry object of the list
    public Entry getLastElem() {
        return entryList.get(entryList.size() - 1);
    }

    //Effects: returns a specific Entry object at the specified index
    public Entry getElem(int idx) {
        return entryList.get(idx);
    }

    //Effects: returns the sum of each entry existing in the list
    public int amount() {
        int sum = 0;
        for (Entry e : entryList) {
            sum += e.getAmount();
        }
        return sum;
    }


    //Effects: returns the size of the list
    public int size() {
        return entryList.size();
    }

    //Effects: returns true if the list is empty, false otherwise
    public boolean isEmpty() {
        return entryList.isEmpty();
    }

    //Effects: returns the index of a specified entry of the list
    public int indexOf(Entry e) {
        return entryList.indexOf(e);
    }

    //Effects: Returns the arraylist of entries
    public ArrayList<Entry> getEntryList() {
        return entryList;
    }

    //returns entrylist as a JsonObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("EntryList", entriesToJson());
        return json;
    }

    //returns arraylist of entries as a json array
    private JSONArray entriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Entry t : entryList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
