package model;

// represents a transaction with cost (CAD), date (YYMMDD), type of good, and
// quantity (#, or false for intangible costs (ex: rent payments)
public class Transaction {
    private double cost;
    private int date;
    private String good;
    private int quantity;

    //TODO: Add Location Class and reference in Transaction

    //EFFECTS: constructs the transaction (cost, date, good, quantity)
    Transaction(double cost, int date, String good, int quantity){
        this.cost = cost;
        this.date = date;
        this.good = good;
        this.quantity = quantity;
    }

    //TODO: Transaction Modify Method //should modify the transaction history list

    //TODO: Transaction Delete Method //should remove a transaction from the history list

    //TODO: View Transaction History Method //return the list

    public double getCost() {
        return cost;
    }
    public int getDate() {
        return date;
    }
    public String getGood() {
        return good;
    }
    public int getQuantity() {
        return quantity;
    }
}
