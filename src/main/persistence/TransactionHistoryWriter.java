package persistence;

import model.transactions.*;
import org.json.JSONObject;


import java.io.*;

/**
 * Represents a writer that writes JSON representation of TransactionHistory to file
 */

public class TransactionHistoryWriter {
    private static final int TAB = 4;  //contains the number of spaces in TAB—for formatting the JSON file
    private static PrintWriter writer;  //Object that handles writing the JSON data to file
    private static String destination;  //pathname for file destination of written JSON data

    // EFFECTS: constructs writer to write to destination file
    public TransactionHistoryWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public static void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // EFFECTS: Handles which type of Transaction we need to write
    public static void writeTransactionHistory(TransactionList transactionList) {

        JSONObject json = transactionList.toJson();
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
