package model.Transactions;

import model.MoneyOutPrimitives.Date;

public class ETransfer extends Transaction{
    private String name;

    public ETransfer(double cost, Date date, String name) {
        super(cost, date);
        this.name = name;
    }

    //Getters
    public String getName() {return this.name;}

    //Setters
    public void setName(String name) {this.name = name;}

}
