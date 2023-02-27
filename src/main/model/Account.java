package model;

import model.MoneyOutPrimitives.Location;

public class Account {
    private double balance;
    private String name;
    private Location address;

    public Account(double balance, String name, Location address) {
        this.balance = balance;
        this.name = name;
        this.address = address;
    }

    //Getters
    public double getBalance() {return this.balance;}
    public String getName() {return this.name;}
    public Location getAddress() {return this.address;}

    //Setters
    public void setBalance(double balance) {this.balance = balance;}
    public void setName(String name) {this.name = name;}
    public void setAddress(Location address) {this.address = address;}
}
