package model;

import model.moneyoutprimitives.Date;
import model.transactions.ETransfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ETransferTest {
    private ETransfer Nolan;
    private ETransfer Bob;

    @BeforeEach
    void runBefore() {
        Nolan = new ETransfer(500, new Date(20230226), "Nolan");
        Bob = new ETransfer(3, new Date(20140329), "Bob");
    }

    @Test
    void testETransfer() {
        assertEquals("Bob", Bob.getName());
        assertEquals("Nolan", Nolan.getName());
    }

    @Test
    void testSetters() {
        Nolan.setName("Eugene");
        assertEquals("Eugene", Nolan.getName());
        Bob.setName("Alex");
        assertEquals("Alex", Bob.getName());
    }

}
