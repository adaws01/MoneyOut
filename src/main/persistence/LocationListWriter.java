package persistence;

import model.moneyoutprimitives.Location;
import model.moneyoutprimitives.LocationList;
import model.transactions.Transaction;
import org.json.JSONObject;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static model.moneyoutprimitives.LocationList.accessLocationList;

/**
 * Represents a writer that writes JSON representation of LocationList to file
 */

public class LocationListWriter {
    private static final int TAB = 4;  //contains the number of spaces in TAB—for formatting the JSON file
    private static PrintWriter writer;  //Object that handles writing the JSON data to file
    private static String destination;  //pathname for file destination of written JSON data

    // EFFECTS: constructs writer to write to destination file
    public LocationListWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public static void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of LocationList to file
    public static void writeLocationList(LocationList locationList) {
        JSONObject json = locationList.toJson();
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
