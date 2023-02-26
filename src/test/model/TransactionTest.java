package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    private Transaction Tomato;
    private Transaction Eggs;
    private Transaction Tomato2;

    @BeforeEach
    void runBefore() {
        Tomato  = new Transaction(2.50, 20230207, "Tomato", 1);
        Eggs    = new Transaction(4, 20210230, "Eggs",   1);
        Tomato2 = new Transaction(4, 20230207, "Tomato", 2);
    }

    @Test
    void testTransaction() {
        assertEquals(2.50, Tomato.getCost());
        assertEquals(20230207, Tomato.getDate());
        assertEquals("Tomato", Tomato.getGood());
        assertEquals(1, Tomato.getQuantity());
    }
}