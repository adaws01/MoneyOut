package model.Transactions;

import model.MoneyOutPrimitives.Date;
import model.MoneyOutPrimitives.Location;

import java.util.ArrayList;
import java.util.List;

// represents a transaction with cost (CAD), date (YYMMDD), type of good, and
// quantity (#, or false for intangible costs (ex: rent payments)
public abstract class Transaction {
    public static List<Transaction> transactionHistory = new ArrayList<>();

    //DELETE THESE WHEN DONE IMPLEMENTING TRANSACTION
    public static Transaction TRANSACTION1 =
            new POSPurchase(1, new Date(20000001), "Tomato", 1, Location.SAVE_ON_DUNBAR);
    public static Transaction TRANSACTION2 =
            new POSPurchase(2, new Date(20000002), "Tomato", 2, Location.SAVE_ON_DUNBAR);
    public static Transaction TRANSACTION3 =
            new ETransfer(3, new Date(20000003), "Person 3");
    public static Transaction TRANSACTION4 =
            new Investment(4, new Date(20000004), "Apple", 4, "Technology");

    private double cost;
    private Date date;

    //EFFECTS: constructs the transaction (cost, date, good, quantity)
    public Transaction(double cost, Date date){
        this.cost = cost;
        this.date = date;
        transactionHistory.add(this);
    }

    //Transaction Delete Method //should remove a transaction from the history list

    //View Transaction History Method //return the list

    //Getters
    public double getCost() {
        return cost;
    }
    public int getDate() {
        return date.getDate();
    }

    //Setters (Test Coverage handled in POSPurchase)
    public void setCost(double cost) {this.cost = cost;}
    public void setDate(Date date) {this.date = date;}
}
