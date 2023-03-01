package model;

import model.MoneyOutPrimitives.Date;
import model.MoneyOutPrimitives.Location;
import model.Transactions.POSPurchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class POSPurchaseTest {
    private POSPurchase Tomato;
    private POSPurchase Eggs;
    private POSPurchase Tomato2;

    @BeforeEach
    void runBefore() {
        Tomato  = new POSPurchase(2.50, new Date(20230207), "Tomato", 1, Location.SAVE_ON_DUNBAR);
        Eggs    = new POSPurchase(4, new Date(20210230), "Eggs",   1, Location.SAVE_ON_DUNBAR);
        Tomato2 = new POSPurchase(4, new Date(20230207), "Tomato", 2, Location.SAFEWAY_4_VINE);
    }

    @Test
    void testPOSPurchaseGetters() {
        assertEquals(2.50, Tomato.getCost());
        assertEquals(20230207, Tomato.getDate());
        assertEquals("Tomato", Tomato.getGood());
        assertEquals(1, Tomato.getQuantity());
        assertEquals(Location.SAVE_ON_DUNBAR, Tomato.getLocation());
    }

    @Test
    void testPOSPurchaseSetters() {
        Eggs.setCost(30);
        assertEquals(30, Eggs.getCost());
        Tomato2.setCost(3);
        assertEquals(3, Tomato2.getCost());
        Eggs.setDate(new Date(20230225));
        assertEquals(20230225, Eggs.getDate());
        Tomato2.setDate(new Date(20211230));
        assertEquals(20211230, Tomato2.getDate());
        Eggs.setGood("Different Good");
        assertEquals("Different Good", Eggs.getGood());
        Tomato2.setGood("Another Good");
        assertEquals("Another Good", Tomato2.getGood());
        Eggs.setQuantity(400);
        assertEquals(400, Eggs.getQuantity());
        Tomato2.setQuantity(4);
        assertEquals(4, Tomato2.getQuantity());
        Eggs.setLocation(Location.SAFEWAY_4_VINE);
        assertEquals(Location.SAFEWAY_4_VINE, Eggs.getLocation());
        Tomato2.setLocation(Location.SAVE_ON_DUNBAR);
        assertEquals(Location.SAVE_ON_DUNBAR, Tomato2.getLocation());
    }
}