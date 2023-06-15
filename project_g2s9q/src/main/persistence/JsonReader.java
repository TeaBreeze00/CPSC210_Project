package persistence;

import model.Entry;
import model.EntryList;
import model.SpendChecker;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads EntryList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads SpendChecker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SpendChecker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSpendChecker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses SpendChecker from JSON object and returns it
    private SpendChecker parseSpendChecker(JSONObject jsonObject) {
        int deposit = jsonObject.getInt("Deposit");
        int limit = jsonObject.getInt("Limit");
        SpendChecker sc = new SpendChecker(deposit, limit);
        JSONObject something = jsonObject.getJSONObject("List of Entries");
        addEntries(sc, something);
        return sc;
    }

    // MODIFIES: wr
    // EFFECTS: parses EntryList from JSON object and adds them to SpendChecker
    private void addEntries(SpendChecker sc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("EntryList");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntrytoList(sc, nextEntry);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses entry from JSON object and adds it to SpendChecker
    private void addEntrytoList(SpendChecker sc, JSONObject jsonObject) {
        int amount = jsonObject.getInt("amount");
        String category = jsonObject.getString("category");
        Entry en = new Entry(category, amount);
        sc.addToList(en);
    }
}