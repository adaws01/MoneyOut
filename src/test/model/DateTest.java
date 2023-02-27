package model;

import model.MoneyOutPrimitives.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateTest {
    private Date Date1;
    private Date Date2;
    private Date Date3;

    @BeforeEach
    void runBefore() {
        Date1 = new Date(20230226);
        Date2 = new Date(19800113);
        Date3 = new Date(20001231);
    }

    @Test
    void testGetDate() {
        assertEquals(20230226, Date1.getDate());
        assertEquals(19800113, Date2.getDate());
        assertEquals(20001231, Date3.getDate());
    }

    @Test
    void testGetYear() {
        assertEquals(2023, Date1.getYear());
        assertEquals(1980, Date2.getYear());
        assertEquals(2000, Date3.getYear());
    }

    @Test
    void testGetMonth() {
        assertEquals(2, Date1.getMonth());
        assertEquals(1, Date2.getMonth());
        assertEquals(12, Date3.getMonth());
    }

    @Test
    void testGetDay() {
        assertEquals(26, Date1.getDay());
        assertEquals(13, Date2.getDay());
        assertEquals(31, Date3.getDay());
    }

}
