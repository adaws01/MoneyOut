package persistence;

import model.transactions.*;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of Transaction to file
public class TransactionHistoryWriter {
    private static final int TAB = 4;
    private static PrintWriter writer;
    private static String destination;

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

        //Transaction transaction = (Transaction) object;
        //String tranClass = String.valueOf(transaction.getClass());
        //if (tranClass.equals("class model.transactions.PosPurchase")) {
        //    writePosPurchase((PosPurchase) transaction);
        //} else if (tranClass.equals("class model.transactions.Investment")) {
        //    writeInvestment((Investment) transaction);
        //} else {
        //    writeETransfer((ETransfer) transaction);
        //}
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of PosPurchase to file
    public static void writePosPurchase(PosPurchase posPurchase) {
        JSONObject json = posPurchase.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of Investment to file
    public static void writeInvestment(Investment investment) {
        JSONObject json = investment.toJson();
        saveToFile(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of ETransfer to file
    public static void writeETransfer(ETransfer etransfer) {
        JSONObject json = etransfer.toJson();
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
