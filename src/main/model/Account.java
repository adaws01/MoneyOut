package model;

import model.MoneyOutPrimitives.Location;

public class Account {
    private double balance;
    private String name;
    private Location address;
    public static Account account = instantiateAccount();

    public Account(double balance, String name, Location address) {
        this.balance = balance;
        this.name = name;
        this.address = address;
    }

    public static Account instantiateAccount() {
        return new Account(0, "Xander", Location.HOME_ADDRESS);
    }

    //REQUIRES: amount <= 0, amount is specified to a max. of two decimal places (ex. 0.00)
    //MODIFIES: this
    //EFFECTS: Adds deposit amount to account balance
    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }

    //REQUIRES: amount <= 0, amount is specified to a max. of two decimal places (ex. 0.00)
    //MODIFIES: this
    //EFFECTS: Subtracts withdrawal amount from account balance
    public void withdraw(double amount) {
        this.balance = this.balance - amount;
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
