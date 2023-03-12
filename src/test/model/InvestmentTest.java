package model;

import model.moneyoutprimitives.Date;
import model.transactions.Investment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvestmentTest {
    private Investment Apple1;
    private Investment Tesla2;
    private Investment Amazon3;

    @BeforeEach
    void runBefore() {
        Apple1 = new Investment(200, new Date(20200413), "Apple", 1, "Technology");
        Tesla2 = new Investment(4000, new Date(20230226), "Tesla", 2, "Technology");
        Amazon3 = new Investment(180, new Date(20100327), "Amazon", 3, "ECommerce");
    }

    @Test
    void testGetCost() {
        assertEquals(200, Apple1.getCost());
        assertEquals(4000, Tesla2.getCost());
    }

    @Test
    void testGetDate() {
        assertEquals(20200413, Apple1.getDate());
        assertEquals(20100327, Amazon3.getDate());
    }

    @Test
    void testGetCompany() {
        assertEquals("Apple", Apple1.getCompany());
        assertEquals("Tesla", Tesla2.getCompany());
    }

    @Test
    void testGetShares() {
        assertEquals(2, Tesla2.getShares());
        assertEquals(3, Amazon3.getShares());
    }

    @Test
    void testGetDomain() {
        assertEquals("Technology", Tesla2.getDomain());
        assertEquals("ECommerce", Amazon3.getDomain());
    }

    @Test
    void testSetters() {
        Apple1.setCompany("Alphabet");
        assertEquals("Alphabet", Apple1.getCompany());
        Tesla2.setCompany("Apple");
        assertEquals("Apple", Tesla2.getCompany());
        Amazon3.setShares(4);
        assertEquals(4, Amazon3.getShares());
        Tesla2.setShares(17);
        assertEquals(17, Tesla2.getShares());
        Tesla2.setDomain("Automotive");
        assertEquals("Automotive", Tesla2.getDomain());
        Apple1.setDomain("Groceries");
        assertEquals("Groceries", Apple1.getDomain());
    }
}
