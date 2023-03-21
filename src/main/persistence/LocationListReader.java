package persistence;

import model.moneyoutprimitives.Location;
import model.moneyoutprimitives.LocationList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Handles the reading and parsing of LocationList data from JSON file.
 */

public class LocationListReader {
    private static String source;  //pathname for .json source file containing LocationList data

    // EFFECTS: constructs reader to read from source file
    public LocationListReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads LocationList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public static LocationList readLocationList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLocationList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private static String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses LocationList from JSON object and returns it
    private static LocationList parseLocationList(JSONObject jsonObject) {
        LocationList ll = LocationList.accessLocationListAsLocationList();
        addLocations(ll, jsonObject);
        return ll;
    }

    // MODIFIES: ll
    // EFFECTS: parses Locations from JSON object and adds them to LocationList
    private static void addLocations(LocationList ll, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("LocationList");
        for (Object json : jsonArray) {
            JSONObject nextLocation = (JSONObject) json;
            addLocation(ll, nextLocation);
        }
    }

    // MODIFIES: ll
    // EFFECTS: parses Location from JSON object and adds it to LocationList
    private static void addLocation(LocationList ll, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String district = jsonObject.getString("district");
        int distanceFromHome = jsonObject.getInt("distanceFromHome");
        Location location = new Location(name, district, distanceFromHome);
        ll.addLocation(location);
    }
}
