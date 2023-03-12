package model;

import model.MoneyOutPrimitives.Date;
import model.MoneyOutPrimitives.Location;
import model.Transactions.*;
import model.Transactions.Helpers.AverageCost;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfTransactionTest {

    static POSPurchase p1;
    static POSPurchase p2;
    static POSPurchase p3;
    static POSPurchase p4;
    static POSPurchase p5;
    static POSPurchase p6;
    static POSPurchase p7;
    static POSPurchase p8;
    static List<POSPurchase> beforeLOTTests = new ArrayList<>();


    @BeforeAll
    static void runBefore() {
        beforeLOTTests.addAll(ListOfTransaction.getPOSPurchaseHistory());
        p1 = new POSPurchase(40, new Date(20230301), "Potato",
                40, Location.SAFEWAY_4_VINE);
        p2 = new POSPurchase(10, new Date(20230307), "Potato",
                10, Location.SAFEWAY_4_VINE);
        Investment i1 = new Investment(400, new Date(20220101), "Apple",
                3, "Technology");
        p3 = new POSPurchase(60, new Date(20230219), "Potato",
                30, Location.SAVE_ON_DUNBAR);
        ETransfer e1 = new ETransfer(800, new Date(20230101), "Landlord");
        p4 = new POSPurchase(8, new Date(20230206), "Potato",
                4, Location.SAVE_ON_DUNBAR);
        p5 = new POSPurchase(40, new Date(20230301), "Glue",
                40, Location.SAFEWAY_4_VINE);
        p6 = new POSPurchase(10, new Date(20230307), "Glue",
                10, Location.SAFEWAY_4_VINE);
        Investment i2 = new Investment(400, new Date(20220101), "Tesla",
                3, "Technology");
        p7 = new POSPurchase(30, new Date(20230219), "Glue",
                60, Location.SAVE_ON_DUNBAR);
        ETransfer e2 = new ETransfer(800, new Date(20230101), "Friend");
        p8 = new POSPurchase(4, new Date(20230206), "Glue",
                8, Location.SAVE_ON_DUNBAR);
    }

    @Test
    void testLocateBestShop() {
        assertEquals(Location.SAFEWAY_4_VINE, ListOfTransaction.locateBestShop("Potato"));
    }

    @Test
    void testGetListOfGoodPurchase() {
        List<POSPurchase> goodPurchases = new ArrayList<>();
        goodPurchases.add(p1);
        goodPurchases.add(p2);
        goodPurchases.add(p3);
        goodPurchases.add(p4);
        assertEquals(goodPurchases, ListOfTransaction.getListOfGoodPurchase("Potato"));
    }

    @Test
    void testOptimizeLocation() {
        POSPurchase p1 = (POSPurchase) ListOfTransaction.transactionHistory.get(0);
        POSPurchase p2 = (POSPurchase) ListOfTransaction.transactionHistory.get(1);
        POSPurchase p3 = (POSPurchase) ListOfTransaction.transactionHistory.get(3);
        POSPurchase p4 = (POSPurchase) ListOfTransaction.transactionHistory.get(5);
        List<POSPurchase> goodPurchases = new ArrayList<>();
        goodPurchases.add(p1);
        goodPurchases.add(p2);
        goodPurchases.add(p3);
        goodPurchases.add(p4);
        assertEquals(Location.SAFEWAY_4_VINE, ListOfTransaction.optimizeLocation(goodPurchases));
    }

    @Test
    void testParseAverageCostList() {
        List<AverageCost> averageCostList = new ArrayList<>();
        AverageCost av1 = new AverageCost(30, 2);
        AverageCost av2 = new AverageCost(50, 4);
        AverageCost av3 = new AverageCost(2, 1);
        averageCostList.add(av1);
        averageCostList.add(av2);
        averageCostList.add(av3);
        assertEquals(2, ListOfTransaction.parseAverageCostList(averageCostList));
    }

    @Test
    void testGetPOSPurchaseHistory() {
        List<POSPurchase> posPurchases = new ArrayList<>();
        posPurchases.addAll(beforeLOTTests);
        posPurchases.add(p1);
        posPurchases.add(p2);
        posPurchases.add(p3);
        posPurchases.add(p4);
        posPurchases.add(p5);
        posPurchases.add(p6);
        posPurchases.add(p7);
        posPurchases.add(p8);
        assertEquals(posPurchases, ListOfTransaction.getPOSPurchaseHistory());
    }
}
