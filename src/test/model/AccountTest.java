package model;

import model.Account;
import model.MoneyOutPrimitives.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private Location WATERYLANE = new Location("6 Watery Lane", "Downtown", 0);
    private Location GASTOWN = new Location("7 Gas Drive", "Burnaby", 0);

    private Account Xander;
    private Account Bob;
    private Account John;

    @BeforeEach
    void runBefore() {
        Xander = new Account(500, "Xander", WATERYLANE);
        Bob = new Account(4237.50, "Bob", Location.SAVE_ON_DUNBAR);
        John = new Account(0.0, "John", GASTOWN);
    }

    @Test
    void testAccountGetters() {
        Xander.setBalance(600);
        Bob.setBalance(4500);
        assertEquals(600, Xander.getBalance());
        assertEquals(4500, Bob.getBalance());

        Bob.setName("Bobby");
        John.setName("Ian");
        assertEquals("Bobby", Bob.getName());
        assertEquals("Ian", John.getName());

        Bob.setAddress(GASTOWN);
        John.setAddress(WATERYLANE);
        assertEquals(GASTOWN, Bob.getAddress());
        assertEquals(WATERYLANE, John.getAddress());
    }

    @Test
    void testAccountSetters() {
        Xander.deposit(300);
        assertEquals(800, Xander.getBalance());

        Bob.withdraw(500.32);
        assertEquals(3737.18, Bob.getBalance());
    }
}
