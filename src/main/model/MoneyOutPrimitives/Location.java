package model.MoneyOutPrimitives;

// Represents a Location in Greater Vancouver, CAN, with name, neighbourhood/district, and
// distance from home in km (input by user, as no network calls are allowed within the scope of this project)
public class Location {
    public static final Location SAVEONDUNBAR =
            new Location("Save On Foods Dunbar", "Dunbar", 1);
    public static final Location SAFEWAY4VINE =
            new Location("Safeway 4th and Vine", "Kitsilano", 6);

    private String name;
    private String district;
    private int distanceFromHome;

    //EFFECTS: Constructs the Location (name, district, distanceFromHome)
    public Location(String name, String district, int distanceFromHome) {
        this.name = name;
        this.district = district;
        this.distanceFromHome = distanceFromHome;
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
