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

public class TransactionHistoryReader {
    private static String source;

    // EFFECTS: constructs reader to read from source file
    public TransactionHistoryReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public static TransactionList readTransactionHistory() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTransactionList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private static String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private static TransactionList parseTransactionList(JSONObject jsonObject) {
        TransactionList tl = TransactionList.accessTransactionHistoryAsTransactionList();
        addTransactions(tl, jsonObject);
        return tl;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private static void addTransactions(TransactionList tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("TransactionHistory");
        for (Object json : jsonArray) {
            JSONObject nextTransaction = (JSONObject) json;
            addTransaction(tl, nextTransaction);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private static void addTransaction(TransactionList tl, JSONObject jsonObject) {
        Transaction transaction = sortTransactionReturn(jsonObject);
        tl.addTransaction(transaction);
    }

    private static Transaction sortTransactionReturn(JSONObject jsonObject) {
        try {
            double cost = jsonObject.getDouble("cost");
            Date date = new Date(jsonObject.getInt("date"));
            String good = jsonObject.getString("good");
            int quantity = jsonObject.getInt("quantity");
            Location location = getLocation(jsonObject);
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
                return new ETransfer(cost, date,name);
            }
        }
    }

    private static Location getLocation(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String district = jsonObject.getString("district");
        int distanceFromHome = jsonObject.getInt("distanceFromHome");
        return new Location(name, district, distanceFromHome);
    }
}
