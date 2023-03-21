package model.transactions;

import model.moneyoutprimitives.Date;
import org.json.JSONObject;

/**
 * Represents an ETransfer: A type of Transaction with cost, date, and name of the person to which money was sent.
 */

public class ETransfer extends Transaction {
    private String name; //Name of the person to whom money was sent

    //REQUIRES: cost > 0
    //EFFECTS: Constructs an ETransfer with cost, date, and name and adds it to ListOfTransaction.transactionHistory
    public ETransfer(double cost, Date date, String name) {
        super(cost, date);
        this.name = name;
    }

    //Getters
    public String getName() {
        return this.name;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    //JSON
    //EFFECTS: Returns a single ETransfer in JSON format.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cost", this.getCost());
        json.put("date", this.getDate());
        json.put("name", name);
        return json;
    }
}
