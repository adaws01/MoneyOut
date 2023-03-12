package model.Transactions;

import model.MoneyOutPrimitives.Date;
import model.MoneyOutPrimitives.Location;

/**
 * Represents a POSPurchase: A type of Transaction with cost, date, good (what item was purchased), quantity,
 *                           and location
 */

public class POSPurchase extends Transaction {
    private String good;        //The type of item that was purchased (Ex: Tomato).
                                // Proper noun capitalization, singular form of word.
    private int quantity;       //Number of units purchased
    private Location location;  //Location of the Transaction

    //REQUIRES: quantity and cost > 0
    //EFFECTS: Constructs a POSPurchase with cost, date, good, quantity, and location,
    //         and adds it to ListOfTransaction.transactionHistory.
    public POSPurchase(double cost, Date date, String good, int quantity, Location location) {
        super(cost, date);
        this.good = good;
        this.quantity = quantity;
        this.location = location;
    }

    //Getters
    public String getGood() {return this.good;}
    public int getQuantity() {return this.quantity;}
    public Location getLocation() {return this.location;}

    //Setters
    public void setGood(String s) {this.good = s;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setLocation(Location location) {this.location = location;}
}
