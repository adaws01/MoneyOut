package persistence;

import model.Account;
import model.moneyoutprimitives.Location;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of Account to file
public class AccountWriter {
    private static final int TAB = 4;
    private static PrintWriter writer;
    private static String destination;

    // EFFECTS: constructs writer to write to destination file
    public AccountWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public static void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Account to file
    public static void writeAccount(Account account) {
        JSONObject json = account.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public static void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private static void saveToFile(String json) {
        writer.print(json);
    }
}
