package model.MoneyOutPrimitives;

import java.util.ArrayList;
import java.util.List;

/**
 *  Represents a Location in Greater Vancouver, CAN, with name, neighbourhood/district, and
 *  distance from home in km (input by user, as no network calls are allowed within the scope of this project)
 */

public class Location {
    //EFFECTS: Declares and Instantiates the list of all locations this app could need to handle
    //         plus helper methods as described by ArrayList.
    public static List<Location> locationList = new ArrayList<>();

    //Instantiation of common shops and Home Address objects for use in MoneyOutApp
    public static Location HOME_ADDRESS =
            new Location("NEED ADDRESS", "DISTRICT", 0);
    public static Location SAVE_ON_DUNBAR =
            new Location("Save On Foods", "Dunbar", 1);
    public static Location SAFEWAY_4_VINE =
            new Location("Safeway 4th and Vine", "Kitsilano", 6);

    private String name;
    private String district;
    private int distanceFromHome;

    //EFFECTS: Constructs the Location (name, district, distanceFromHome) and adds it to the locationList
    public Location(String name, String district, int distanceFromHome) {
        this.name = name;
        this.district = district;
        this.distanceFromHome = distanceFromHome;
        locationList.add(this);
    }

    //Getters
    public String getName(){return name;}
    public String getDistrict(){return district;}
    public int getDistanceFromHome(){return distanceFromHome;}

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
}
