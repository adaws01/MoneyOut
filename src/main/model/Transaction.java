package model;

// represents a transaction with cost (CAD), date (YYMMDD), type of good, and
// quantity (#, or false for intangible costs (ex: rent payments)
public class Transaction {
    private float cost;
    private int date;
    private String good;
    private int quantity;

    //TODO: Add Location Class and reference in Transaction

    //EFFECTS: constructs the transaction (cost, date, good, quantity)
    Transaction(float cost, int date, String good, int quantity){
        this.cost = cost;
        this.date = date;
        this.good = good;
        this.quantity = quantity;
    }

    //TODO: Transaction Modify Method

    //TODO: Transaction Delete Method

    public float getCost() {
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
