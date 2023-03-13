package persistence;

import model.Account;
import model.moneyoutprimitives.Location;
import model.transactions.ETransfer;
import model.transactions.Investment;
import model.transactions.PosPurchase;
import model.transactions.Transaction;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of Location, Transactions and subclass, and Account to file
public abstract class JsonWriter {
    static final int TAB = 4;
    static PrintWriter writer;
    static String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public static void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(Object object) {

    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public static void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    void saveToFile(String json) {
        writer.print(json);
    }
}
