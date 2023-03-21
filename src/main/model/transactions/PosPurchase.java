package model.transactions;

import model.moneyoutprimitives.Date;
import model.moneyoutprimitives.Location;
import org.json.JSONObject;

/**
 * Represents a POSPurchase: A type of Transaction with cost, date, good (what item was purchased), quantity,
 * and location
 */

public class PosPurchase extends Transaction {
    private String good;        //The type of item that was purchased (Ex: Tomato).
                                // Proper noun capitalization, singular form of word.
    private int quantity;       //Number of units purchased
    private Location location;  //Location of the Transaction

    //REQUIRES: quantity and cost > 0
    //EFFECTS: Constructs a POSPurchase with cost, date, good, quantity, and location,
    //         and adds it to ListOfTransaction.transactionHistory.
    public PosPurchase(double cost, Date date, String good, int quantity, Location location) {
        super(cost, date);
        this.good = good;
        this.quantity = quantity;
        this.location = location;
    }

    //Getters
    public String getGood() {
        return this.good;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Location getLocation() {
        return this.location;
    }

    //Setters
    public void setGood(String s) {
        this.good = s;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    //JSON
    //EFFECTS: Returns a single PosPurchase in JSON format
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cost", this.getCost());
        json.put("date", this.getDate());
        json.put("good", good);
        json.put("quantity", quantity);
        json.put("location", location.toJson());
        return json;
    }
}
