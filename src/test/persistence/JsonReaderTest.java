package persistence;

import model.Account;
import model.moneyoutprimitives.LocationList;
import model.transactions.Transaction;
import model.transactions.TransactionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

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

    //@Test
    //void testReadEmptyTransactionHistory() {
    //    TransactionHistoryReader thr = new TransactionHistoryReader("./data/TestEmptyTransactionHistory.json");
    //}
}
