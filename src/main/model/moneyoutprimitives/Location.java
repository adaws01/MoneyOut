package model.moneyoutprimitives;

import model.transactions.Transaction;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

import static model.moneyoutprimitives.LocationList.accessLocationList;

/**
 * Represents a Location with name, neighbourhood/district, and
 * distance from home in km (input by user, as no network calls are allowed within the scope of this project)
 */

public class Location implements Writable {

    //Instantiation of common shops and Home Address objects for use in MoneyOutApp
    private static Location HOME_ADDRESS =
            new Location("NEED ADDRESS", "DISTRICT", 0);
    private static Location SAVE_ON_DUNBAR =
            new Location("Save On Foods", "Dunbar", 1);
    private static Location SAFEWAY_4_VINE =
            new Location("Safeway 4th and Vine", "Kitsilano", 6);

    private String name;  //Name assigned to the location. This could be an address, or a description of the location.
    private String district;  //Neighbourhood that the location is in. This is flexible and could also represent
    //a city, or any more general description of where the location is.
    private int distanceFromHome; //The distance from the user's home. Must be updated manually if home address changed

    //EFFECTS: Constructs the Location (name, district, distanceFromHome) and adds it to the locationList
    public Location(String name, String district, int distanceFromHome) {
        this.name = name;
        this.district = district;
        this.distanceFromHome = distanceFromHome;
        accessLocationList().add(this);
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public int getDistanceFromHome() {
        return distanceFromHome;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setDistanceFromHome(int distanceFromHome) {
        this.distanceFromHome = distanceFromHome;
    }

    //Accessor Methods



    public static Location accessHomeAddress() {
        return HOME_ADDRESS;
    }

    public static Location accessSaveOnDunbar() {
        return SAVE_ON_DUNBAR;
    }

    public static Location accessSafewayFourthVine() {
        return SAFEWAY_4_VINE;
    }

    //JSON Data
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("district", district);
        json.put("distanceFromHome", distanceFromHome);
        return json;
    }
}
