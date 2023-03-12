package model.transactions;

import model.moneyoutprimitives.Date;

/**
 * Represents an Investment: A type of Transaction with cost, date, company invested in, number of shares purchased,
 * and the domain that the company works in.
 */

public class Investment extends Transaction {
    private String company; //Company invested in
    private String domain;  //Company's main parent industry
    private int shares;     //Number of Shares purchased

    //REQUIRES: cost and shares > 0
    //EFFECTS: Constructs the Investment with cost, date, company, shares, and domain,
    //         and adds the Investment to ListOfTransaction.transactionHistory
    public Investment(double cost, Date date, String company, int shares, String domain) {
        super(cost, date);
        this.company = company;
        this.shares = shares;
        this.domain = domain;
    }

    //Getters
    public String getCompany() {
        return this.company;
    }

    public int getShares() {
        return this.shares;
    }

    public String getDomain() {
        return this.domain;
    }

    //Setters
    public void setCompany(String company) {
        this.company = company;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
