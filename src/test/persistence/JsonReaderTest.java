package persistence;

import model.Account;
import model.moneyoutprimitives.Location;
import model.moneyoutprimitives.LocationList;
import model.transactions.Investment;
import model.transactions.Transaction;
import model.transactions.TransactionList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static model.moneyoutprimitives.LocationList.wipeLocationList;
import static model.transactions.TransactionList.wipeTransactionHistory;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @BeforeEach
    void runBefore() {
        wipeTransactionHistory();
        wipeLocationList();
    }

    @Test
    void testTransactionHistoryReaderNonExistentFile() {
        TransactionHistoryReader thr = new TransactionHistoryReader("./data/NonExistentFile.json");

        try {
            TransactionList transactions = thr.readTransactionHistory();
            fail("Expected Exception -> Non Existent Data File cannot be read");
        } catch (IOException e) {
            //pass test
        }
    }

    @Test
    void testReaderNonExistentFile() {
        LocationListReader llr = new LocationListReader("./data/NonExistentFile.json");

        try {
            LocationList locations = llr.readLocationList();
            fail("Expected Exception -> Non Existent Data File cannot be read");
        } catch (IOException e) {
            //pass test
        }
    }

    @Test
    void testAccountReaderNonExistentFile() {
        AccountReader ar = new AccountReader("./data/NonExistentFile.json");

        try {
            Account account = ar.readAccount();
            fail("Expected Exception -> Non Existent Data File cannot be read");
        } catch (IOException e) {
            //pass test
        }
    }

    @Test
    void testReadEmptyTransactionHistory() {
        TransactionHistoryReader thr = new TransactionHistoryReader("./data/TestEmptyTransactionHistory.json");
        try {
            TransactionList transactionList = TransactionHistoryReader.readTransactionHistory();
            assertEquals(0, transactionList.getList().size());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    void testReadRegularTransactionHistory() {
        TransactionHistoryReader thr = new TransactionHistoryReader("./data/TestRegularTransactionHistory.json");
        try {
            TransactionList transactionList = TransactionHistoryReader.readTransactionHistory();
            ArrayList<Transaction> ourList = transactionList.getList();
            assertEquals(4, ourList.size());
            Investment secondTransaction = (Investment) ourList.get(1);
            assertEquals(50, secondTransaction.getCost());
            assertEquals(20230320, secondTransaction.getDate());
            assertEquals(1, secondTransaction.getShares());
            assertEquals("Tesla", secondTransaction.getCompany());
            assertEquals("Technology", secondTransaction.getDomain());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }

    @Test
    void testReadRegularLocationList() {
        LocationListReader llr = new LocationListReader("./data/TestRegularLocationList.json");
        try {
            LocationList locationList = LocationListReader.readLocationList();
            ArrayList<Location> ourList = locationList.getList();
            assertEquals(2, ourList.size());
            Location firstJsonLocation = ourList.get(0);
            assertEquals("Grocery Store A", firstJsonLocation.getName());
            assertEquals("Downtown", firstJsonLocation.getDistrict());
            assertEquals(10, firstJsonLocation.getDistanceFromHome());
        } catch (IOException e) {
            fail("Could not read from file");
        }
    }
}
