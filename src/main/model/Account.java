package model;

import model.moneyoutprimitives.Location;
import org.json.JSONObject;
import persistence.Writable;

/**
 * Represents an Account with account balance, name of account owner, and the home address of the account owner.
 * A single account (named account) is instantiated for use across the entire application.
 * All instantiated Transactions update account balance.
 */

public class Account implements Writable {
    private double balance;   //Account Balance
    private String name;      //Name of Account Owner
    private Location address; //Account Owner's Home Address
    private static Account account = instantiateAccount(); //Declaration of account object

    //EFFECTS: Constructs an account with account balance, name of account owner, and owner's address.
    public Account(double balance, String name, Location address) {
        this.balance = balance;
        this.name = name;
        this.address = address;
    }

    //EFFECTS: Instantiates the single account used across the application with initial balance = 0,
    //         name = "Xander", and a shell home address location to be modified within the application.
    public static Account instantiateAccount() {
        return new Account(0, "Xander", Location.accessHomeAddress());
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
    public double getBalance() {
        return this.balance;
    }

    public String getName() {
        return this.name;
    }

    public Location getAddress() {
        return this.address;
    }

    //Setters
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    //Accessor Methods
    public static Account accessAccount() {
        return account;
    }

    //JSON
    //EFFECTS: Returns Account information in JSON format. Overrides Writable toJson() method.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("balance", balance);
        json.put("name", name);
        json.put("address", address.toJson());
        return json;
    }
}

//TODO: Fix Account Home Location Information (not updating)
