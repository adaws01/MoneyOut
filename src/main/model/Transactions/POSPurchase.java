package model.Transactions;

import model.MoneyOutPrimitives.Date;
import model.MoneyOutPrimitives.Location;

public class POSPurchase extends Transaction {
    private String good;
    private int quantity;
    private Location location;

    //REQUIRES: Quantity > 0
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
