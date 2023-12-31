package model.transactions;

import model.Event;
import model.EventLog;
import model.Loading;
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
        if (!Loading.isLoading() == true) {
            EventLog.getInstance().logEvent(new Event("Logged New POS Purchase with Cost: " + cost
                    + ", Date: " + date.getDate() + ", Good: " + good + ", Quantity: "
                    + quantity + ", At: " + location.getName() + "."));
        }
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
        if (!Loading.isLoading() == true) {
            EventLog.getInstance().logEvent(new Event("Changed POS Purchase Good from " + this.getGood()
                    + " to " + s + "."));
        }
        this.good = s;
    }

    public void setQuantity(int quantity) {
        if (!Loading.isLoading() == true) {
            EventLog.getInstance().logEvent(new Event("Changed POS Purchase Quantity from "
                    + this.getQuantity() + " to " + quantity + "."));
        }
        this.quantity = quantity;
    }

    public void setLocation(Location location) {
        if (!Loading.isLoading() == true) {
            EventLog.getInstance().logEvent(new Event("Changed POS Purchase Location from "
                    + this.getLocation().getName() + " to " + location.getName() + "."));
        }
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
