package persistence;

import model.Account;
import model.moneyoutprimitives.Date;
import model.moneyoutprimitives.Location;
import model.transactions.ETransfer;
import model.transactions.Investment;
import model.transactions.PosPurchase;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkDate(int dateInt, Date date) {
        assertEquals(dateInt, date.getDate());
    }

    protected void checkLocation(String name, String district, int distanceFromHome, Location location) {
        assertEquals(name, location.getName());
        assertEquals(district, location.getDistrict());
        assertEquals(distanceFromHome, location.getDistanceFromHome());
    }

    protected void checkPosPurchase(double cost, Date date, String good, int quantity, Location location,
                                    PosPurchase posPurchase) {
        assertEquals(cost, posPurchase.getCost());
        assertEquals(date, posPurchase.getDate());
        assertEquals(good, posPurchase.getGood());
        assertEquals(quantity, posPurchase.getQuantity());
        assertEquals(location, posPurchase.getLocation());
    }

    protected void checkInvestment(double cost, Date date, String company, int shares, String domain,
                                    Investment investment) {
        assertEquals(cost, investment.getCost());
        assertEquals(date, investment.getDate());
        assertEquals(company, investment.getCompany());
        assertEquals(shares, investment.getShares());
        assertEquals(domain, investment.getDomain());
    }

    protected void checkETransfer(double cost, Date date, String name, ETransfer etransfer) {
        assertEquals(cost, etransfer.getCost());
        assertEquals(date, etransfer.getDate());
        assertEquals(name, etransfer.getName());
    }

    protected void checkETransfer(double balance, String name, Location address, Account account) {
        assertEquals(balance, account.getBalance());
        assertEquals(name, account.getName());
        assertEquals(address, account.getAddress());
    }
}
