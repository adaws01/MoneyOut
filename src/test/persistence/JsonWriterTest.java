package persistence;

import model.Account;
import model.moneyoutprimitives.Date;
import model.moneyoutprimitives.Location;
import model.moneyoutprimitives.LocationList;
import model.transactions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static model.moneyoutprimitives.LocationList.addLocation;
import static model.moneyoutprimitives.LocationList.wipeLocationList;
import static model.transactions.TransactionList.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @BeforeEach
    void runBefore() {
        wipeTransactionHistory();
        wipeLocationList();
    }

    @Test
    void testTransactionHistoryWriterInvalidFile() {
        try {
            TransactionHistoryWriter writer = new TransactionHistoryWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Expected Exception -> Non Existent Data File cannot be opened");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testLocationListWriterInvalidFile() {
        try {
            LocationListWriter writer = new LocationListWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Expected Exception -> Non Existent Data File cannot be opened");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testAccountWriterInvalidFile() {
        try {
            AccountWriter writer = new AccountWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Expected Exception -> Non Existent Data File cannot be opened");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testAccountWriter() {
        try {
            AccountWriter writer = new AccountWriter("./data/TestAccount.json");
            writer.open();
            writer.writeAccount(Account.accessAccount());
            writer.close();

            AccountReader ar = new AccountReader("./data/TestAccount.json");
            ar.readAccount();
            Account account = Account.accessAccount();
            assertEquals(0, account.getBalance());
            assertEquals("Xander", account.getName());
        } catch (IOException e) {
            fail("Exception not expected");
        }

    }

    @Test
    void testWriterEmptyTransactionHistory() {
        try {
            TransactionHistoryWriter writer = new TransactionHistoryWriter("./data/TestEmptyTransactionHistory.json");
            writer.open();
            writer.writeTransactionHistory(accessTransactionHistoryAsTranList());
            writer.close();

            TransactionHistoryReader thr = new TransactionHistoryReader("./data/TestEmptyTransactionHistory.json");
            thr.readTransactionHistory();
            TransactionList transactionList = TransactionHistoryReader.readTransactionHistory();
            assertEquals(0, transactionList.getList().size());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testWriterEmptyLocationList() {
        try {
            LocationListWriter writer = new LocationListWriter("./data/TestEmptyLocationList.json");
            writer.open();
            writer.writeLocationList(LocationList.accessLocationListAsLocationList());
            writer.close();

            LocationListReader llr = new LocationListReader("./data/TestEmptyLocationList.json");
            LocationList locationList = llr.readLocationList();
            assertEquals(0, locationList.getList().size());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testWriterRegularTransactionHistory() {
        try {
            PosPurchase firstTransaction = new PosPurchase(2, new Date(20230320), "Toothbrush",
                    1, new Location("Grocery Store A", "Downtown", 10));
            Investment secondTransaction = new Investment(50, new Date(20230320), "Tesla", 1,
                    "Technology");
            ETransfer thirdTransaction = new ETransfer(500, new Date(20230228), "Landlord");
            PosPurchase fourthTransaction = new PosPurchase(10, new Date(20230320), "Tea",
                    20, new Location("Shop 2", "Kitsilano", 6));
            addTransaction(firstTransaction);
            addTransaction(secondTransaction);
            addTransaction(thirdTransaction);
            addTransaction(fourthTransaction);
            TransactionHistoryWriter writer = new TransactionHistoryWriter("./data/TestRegularTransactionHistory.json");
            writer.open();
            writer.writeTransactionHistory(accessTransactionHistoryAsTranList());
            writer.close();

            TransactionList transactionList = TransactionHistoryReader.readTransactionHistory();
            ArrayList<Transaction> ourList = transactionList.getList();
            assertEquals(4, ourList.size());
            Investment secondJsonTransaction = (Investment) ourList.get(1);
            assertEquals(50, secondJsonTransaction.getCost());
            assertEquals(20230320, secondJsonTransaction.getDate());
            assertEquals(1, secondJsonTransaction.getShares());
            assertEquals("Tesla", secondJsonTransaction.getCompany());
            assertEquals("Technology", secondJsonTransaction.getDomain());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testWriterRegularLocationList() {
        try {
            Location firstLocation = new Location("Grocery Store A", "Downtown", 10);
            Location secondLocation = new Location("Shop 2", "Kitsilano", 6);
            addLocation(firstLocation);
            addLocation(secondLocation);
            LocationListWriter writer = new LocationListWriter("./data/TestRegularLocationList.json");
            writer.open();
            writer.writeLocationList(LocationList.accessLocationListAsLocationList());
            writer.close();

            LocationListReader llr = new LocationListReader("./data/TestRegularLocationList.json");
            LocationList locationList = llr.readLocationList();
            ArrayList<Location> ourList = locationList.getList();
            Location firstJsonLocation = ourList.get(0);
            assertEquals("Grocery Store A", firstJsonLocation.getName());
            assertEquals("Downtown", firstJsonLocation.getDistrict());
            assertEquals(10, firstJsonLocation.getDistanceFromHome());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }
}
