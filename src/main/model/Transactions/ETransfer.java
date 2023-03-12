package model.Transactions;

import model.MoneyOutPrimitives.Date;

/**
 * Represents an ETransfer: A type of Transaction with cost, date, and name of the person to which money was sent.
 */

public class ETransfer extends Transaction{
    private String name;

    //REQUIRES: cost > 0
    //EFFECTS: Constructs an ETransfer with cost, date, and name and adds it to ListOfTransaction.transactionHistory
    public ETransfer(double cost, Date date, String name) {
        super(cost, date);
        this.name = name;
    }

    //Getters
    public String getName() {return this.name;}

    //Setters
    public void setName(String name) {this.name = name;}

}
