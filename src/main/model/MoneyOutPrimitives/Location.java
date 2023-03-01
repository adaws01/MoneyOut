package model.MoneyOutPrimitives;

import java.util.ArrayList;
import java.util.List;

// Represents a Location in Greater Vancouver, CAN, with name, neighbourhood/district, and
// distance from home in km (input by user, as no network calls are allowed within the scope of this project)
public class Location {
    public static Location HOME_ADDRESS =
            new Location("ADDRESS/NAME", "DISTRICT", 0);
    public static Location SAVE_ON_DUNBAR =
            new Location("Save On Foods", "Dunbar", 1);
    public static Location SAFEWAY_4_VINE =
            new Location("Safeway 4th and Vine", "Kitsilano", 6);

    private String name;
    private String district;
    private int distanceFromHome;

    private List<Location> locationList = new ArrayList<Location>();

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
    public List<Location> getLocationList() {return locationList;}

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
