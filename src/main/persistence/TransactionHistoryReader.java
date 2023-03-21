package persistence;

import model.moneyoutprimitives.Date;
import model.moneyoutprimitives.Location;
import model.transactions.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Handles Reading and Parsing TransactionHistory from JSON source file
 */

public class TransactionHistoryReader {
    private static String source;  //pathname of JSON source file

    // EFFECTS: constructs reader to read from source file
    public TransactionHistoryReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads TransactionHistory from file and returns it;
    // throws IOException if an error occurs reading data from file
    public static TransactionList readTransactionHistory() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTransactionList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private static String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses TransactionHistory from JSON object and returns it
    private static TransactionList parseTransactionList(JSONObject jsonObject) {
        TransactionList tl = TransactionList.accessTransactionHistoryAsTranList();
        addTransactions(tl, jsonObject);
        return tl;
    }

    // MODIFIES: tl
    // EFFECTS: parses Transactions from JSON object and adds them to TransactionHistory
    private static void addTransactions(TransactionList tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("TransactionHistory");
        for (Object json : jsonArray) {
            JSONObject nextTransaction = (JSONObject) json;
            addTransaction(tl, nextTransaction);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses Transaction from JSON object and adds it to TransactionHistory
    private static void addTransaction(TransactionList tl, JSONObject jsonObject) {
        Transaction transaction = sortTransactionReturn(jsonObject);
        tl.addTransaction(transaction);
    }

    //EFFECTS: Handles the differences in data structure between Transaction subclasses
    private static Transaction sortTransactionReturn(JSONObject jsonObject) {
        try {
            double cost = jsonObject.getDouble("cost");
            Date date = new Date(jsonObject.getInt("date"));
            String good = jsonObject.getString("good");
            int quantity = jsonObject.getInt("quantity");
            Location location = openLocation(jsonObject);
            return new PosPurchase(cost, date, good, quantity, location);
        } catch (Exception e) {
            try {
                double cost = jsonObject.getDouble("cost");
                Date date = new Date(jsonObject.getInt("date"));
                String company = jsonObject.getString("company");
                int shares = jsonObject.getInt("shares");
                String domain = jsonObject.getString("domain");
                return new Investment(cost, date, company, shares, domain);
            } catch (Exception f) {
                double cost = jsonObject.getDouble("cost");
                Date date = new Date(jsonObject.getInt("date"));
                String name = jsonObject.getString("name");
                return new ETransfer(cost, date, name);
            }
        }
    }

    //EFFECTS: Handles parsing any location data built into individual Transactions
    private static Location openLocation(JSONObject jsonObject) {
        JSONObject jsonObject2 = jsonObject.getJSONObject("location");
        return getLocation(jsonObject2);
    }

    //EFFECTS: Helper for openLocation(). returns Location data from JSON file.
    private static Location getLocation(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String district = jsonObject.getString("district");
        int distanceFromHome = jsonObject.getInt("distanceFromHome");
        return new Location(name, district, distanceFromHome);
    }
}
