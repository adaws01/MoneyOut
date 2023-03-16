package model.moneyoutprimitives;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a List of Location
 * listLocation instantiated for use throughout the entire application
 */

public class LocationList implements Writable {

    //EFFECTS: Declares and Instantiates the list of all locations this app could need to handle
    //         plus helper methods as described by ArrayList.

    private static LocationList locationList = new LocationList(new ArrayList<>());

    private ArrayList<Location> list;

    public LocationList(ArrayList<Location> list) {
        this.list = list;
    }

    public static List<Location> accessLocationList() {
        return locationList.list;
    }

    public static LocationList accessLocationListAsLocationList() {
        return locationList;
    }

    public static void addLocation(Location location) {
        locationList.list.add(location);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("LocationList", locationsToJson());
        return json;
    }

    // EFFECTS: returns things in locationList as a JSON array
    private JSONArray locationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Location l : locationList.list) {
            jsonArray.put(l.toJson());
        }

        return jsonArray;
    }
}
