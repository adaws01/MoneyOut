package model;

// Represents a Location in Greater Vancouver, CAN, with name, neighbourhood/district, and
// distance from home (input by user, as no network calls are allowed within the scope of this project)
public class Location {
    private String name;
    private String district;
    private int distanceFromHome;

    //EFFECTS: Constructs the Location (name, district, distanceFromHome)
    Location(String name, String district, int distanceFromHome) {
        this.name = name;
        this.district = district;
        this.distanceFromHome = distanceFromHome;
    }

    //Getters
    public String getName(){return name;}
    public String getDistrict(){return district;}
    public int getDistanceFromHome(){return distanceFromHome;}

    //TODO: Setters

}
