package model.Transactions;

import model.MoneyOutPrimitives.Date;

public class Investment extends Transaction{
    private String company;
    private String domain;
    private int shares;

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
    public int getShares() {return this.shares;}
    public String getDomain() {return this.domain;}

    //Setters
    public void setCompany(String company) {this.company = company;}
    public void setShares(int shares) {this.shares = shares;}
    public void setDomain(String domain) {this.domain = domain;}
}
