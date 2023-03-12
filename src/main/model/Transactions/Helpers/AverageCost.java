package model.Transactions.Helpers;

/**
 * Represents the average cost of an item at a certain location, with net cost and net quantity.
 * Implemented in ListOfTransaction optimizeLocation() method.
 */

public class AverageCost {

    private double cost;
    private int quantity;

    //EFFECTS: Constructs the AverageCost (cost, quantity) of an item at a location
    //         as defined in ListOfTransaction.optimizeLocation()
    public AverageCost(double cost, int quantity) {
        this.cost = cost;
        this.quantity = quantity;
    }

    //GETTERS
    public double getCost() {
        return cost;
    }
    public int getQuantity() {
        return quantity;
    }

    //SETTERS
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
