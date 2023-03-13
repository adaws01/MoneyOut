package model;

import model.moneyoutprimitives.Date;
import model.moneyoutprimitives.Location;
import model.transactions.*;
import model.transactions.AverageCost;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfTransactionTest {

    static PosPurchase p1;
    static PosPurchase p2;
    static PosPurchase p3;
    static PosPurchase p4;
    static PosPurchase p5;
    static PosPurchase p6;
    static PosPurchase p7;
    static PosPurchase p8;
    static Investment i1;
    static Investment i2;
    static ETransfer e1;
    static ETransfer e2;
    static List<PosPurchase> beforeLOTTests = new ArrayList<>();


    @BeforeAll
    static void runBefore() {
        beforeLOTTests.addAll(TransactionList.getPosPurchaseHistory());
        p1 = new PosPurchase(40, new Date(20230301), "Potato",
                40, Location.accessSafewayFourthVine());
        p2 = new PosPurchase(10, new Date(20230307), "Potato",
                10, Location.accessSafewayFourthVine());
        i1 = new Investment(400, new Date(20220101), "Apple",
                3, "Technology");
        p3 = new PosPurchase(60, new Date(20230219), "Potato",
                30, Location.accessSaveOnDunbar());
        e1 = new ETransfer(800, new Date(20230101), "Landlord");
        p4 = new PosPurchase(8, new Date(20230206), "Potato",
                4, Location.accessSaveOnDunbar());
        p5 = new PosPurchase(40, new Date(20230301), "Glue",
                40, Location.accessSafewayFourthVine());
        p6 = new PosPurchase(10, new Date(20230307), "Glue",
                10, Location.accessSafewayFourthVine());
        i2 = new Investment(400, new Date(20220101), "Tesla",
                3, "Technology");
        p7 = new PosPurchase(30, new Date(20230219), "Glue",
                60, Location.accessSaveOnDunbar());
        e2 = new ETransfer(800, new Date(20230101), "Friend");
        p8 = new PosPurchase(4, new Date(20230206), "Glue",
                8, Location.accessSaveOnDunbar());
        TransactionList.accessTransactionHistory().add(p1);
        TransactionList.accessTransactionHistory().add(p2);
        TransactionList.accessTransactionHistory().add(i1);
        TransactionList.accessTransactionHistory().add(p3);
        TransactionList.accessTransactionHistory().add(e1);
        TransactionList.accessTransactionHistory().add(p4);
        TransactionList.accessTransactionHistory().add(p5);
        TransactionList.accessTransactionHistory().add(p6);
        TransactionList.accessTransactionHistory().add(i2);
        TransactionList.accessTransactionHistory().add(p7);
        TransactionList.accessTransactionHistory().add(e2);
        TransactionList.accessTransactionHistory().add(p8);
    }

    @Test
    void testLocateBestShop() {
        assertEquals(Location.accessSafewayFourthVine().getName(), TransactionList.locateBestShopFor("Potato").getName());
    }

    @Test
    void testGetListOfGoodPurchase() {
        List<PosPurchase> goodPurchases = new ArrayList<>();
        goodPurchases.add(p1);
        goodPurchases.add(p2);
        goodPurchases.add(p3);
        goodPurchases.add(p4);
        assertEquals(goodPurchases, TransactionList.getListOfGoodPurchase("Potato"));
    }

    @Test
    void testOptimizeLocation() {
        PosPurchase p1 = (PosPurchase) TransactionList.accessTransactionHistory().get(0);
        PosPurchase p2 = (PosPurchase) TransactionList.accessTransactionHistory().get(1);
        PosPurchase p3 = (PosPurchase) TransactionList.accessTransactionHistory().get(3);
        PosPurchase p4 = (PosPurchase) TransactionList.accessTransactionHistory().get(5);
        List<PosPurchase> goodPurchases = new ArrayList<>();
        goodPurchases.add(p1);
        goodPurchases.add(p2);
        goodPurchases.add(p3);
        goodPurchases.add(p4);
        assertEquals(Location.accessSafewayFourthVine().getName(), TransactionList.optimizeLocation(goodPurchases).getName());
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
        assertEquals(2, TransactionList.parseAverageCostList(averageCostList));
    }

    @Test
    void testGetPOSPurchaseHistory() {
        List<PosPurchase> posPurchases = new ArrayList<>(beforeLOTTests);
        posPurchases.add(p1);
        posPurchases.add(p2);
        posPurchases.add(p3);
        posPurchases.add(p4);
        posPurchases.add(p5);
        posPurchases.add(p6);
        posPurchases.add(p7);
        posPurchases.add(p8);
        assertEquals(posPurchases, TransactionList.getPosPurchaseHistory());
    }

    @Test
    void testTransactionsOverLastMonth() {
        List<Transaction> lastMonth = new ArrayList<>();
        lastMonth.add(p1);
        lastMonth.add(p2);
        lastMonth.add(p5);
        lastMonth.add(p6);
        assertEquals(lastMonth, TransactionList.getTransactionsOverLastMonth(new Date(20230331)));
        assertEquals(4, TransactionList.countTransactionsOverLastMonth(new Date(20230331)));
    }
}
