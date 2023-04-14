package model.transactions;

import model.Event;
import model.EventLog;
import model.Loading;
import model.moneyoutprimitives.Date;
import org.json.JSONObject;

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
        if (!Loading.isLoading() == true) {
            EventLog.getInstance().logEvent(new Event("Logged New Investment with Cost: " + cost
                    + ", Date: " + date.getDate() + ", Company: " + company + ", Shares: "
                    + shares + ", in Domain: " + domain + "."));
        }
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
        if (!Loading.isLoading() == true) {
            EventLog.getInstance().logEvent(new Event("Changed Investment Company from " + this.getCompany()
                    + " to " + company + "."));
        }
        this.company = company;
    }

    public void setShares(int shares) {
        if (!Loading.isLoading() == true) {
            EventLog.getInstance().logEvent(new Event("Changed Investment Shares from " + this.getShares()
                    + " to " + shares + "."));
        }
        this.shares = shares;
    }

    public void setDomain(String domain) {
        if (!Loading.isLoading() == true) {
            EventLog.getInstance().logEvent(new Event("Changed Investment Domain from " + this.getDomain()
                    + " to " + domain + "."));
        }
        this.domain = domain;
    }

    //JSON
    //EFFECTS: Returns a single investment in JSON format.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cost", this.getCost());
        json.put("date", this.getDate());
        json.put("company", company);
        json.put("shares", shares);
        json.put("domain", domain);
        return json;
    }
}
