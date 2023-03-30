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

    private ArrayList<Location> list; //list parameter for LocationList constructor

    //EFFECTS: Constructs a new LocationList containing an ArrayList of Location.
    //This implementation was created before I learned how to override an equals method.
    public LocationList(ArrayList<Location> list) {
        this.list = list;
    }

    //EFFECTS: Getter that returns an ArrayList<Location> object
    public static List<Location> accessLocationList() {
        return locationList.list;
    }

    //EFFECTS: Getter that returns a LocationList object.
    public static LocationList accessLocationListAsLocationList() {
        return locationList;
    }

    //EFFECTS: Adds input location to the end of the global LocationList.
    public static void addLocation(Location location) {
        locationList.list.add(location);
    }

    //MODIFIES: locationList
    //EFFECTS: Wipes locationList. Used in Testing to refresh locationList before each test.
    public static void wipeLocationList() {
        locationList.list.clear();
    }

    //GETTER
    public ArrayList<Location> getList() {
        return this.list;
    }

    //EFFECTS: Returns a list of Location in JSON format.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("LocationList", locationsToJson());
        return json;
    }

    // EFFECTS: returns Locations in locationList as a JSON array
    private JSONArray locationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Location l : locationList.list) {
            jsonArray.put(l.toJson());
        }

        return jsonArray;
    }
}
