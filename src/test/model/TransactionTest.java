package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    Transaction Tomato;
    Transaction Eggs;
    Transaction Tomato2;

    @BeforeEach
    void runBefore() {
        Tomato  = new Transaction(2, 230207, "Tomato", 1);
        Eggs    = new Transaction(4, 210230, "Eggs",   1);
        Tomato2 = new Transaction(4, 230207, "Tomato", 2);
    }

    @Test
    void testTransaction() {
        assertEquals(2, Tomato.getCost());
        assertEquals(230207, Tomato.getDate());
        assertEquals("Tomato", Tomato.getGood());
        assertEquals(1, Tomato.getQuantity());
    }
}