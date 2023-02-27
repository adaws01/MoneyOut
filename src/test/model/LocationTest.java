package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    private Location SaveOnDunbar;
    private Location UBCFoodHub;
    private Location TomLeeGranville;

    @BeforeEach
    void runBefore() {
        SaveOnDunbar = new Location("Save On Foods Dunbar", "Dunbar", 1);
        UBCFoodHub = new Location("UBC Food Hub Market", "University Endowment Lands", 7);
        TomLeeGranville = new Location("Tom Lee", "Downtown", 10);
    }

    @Test
    void testGetName(){
        assertEquals("Save On Foods Dunbar", SaveOnDunbar.getName());
        assertEquals("UBC Food Hub Market", UBCFoodHub.getName());
        assertEquals("Tom Lee", TomLeeGranville.getName());
    }

    @Test
    void testGetDistrict(){
        assertEquals("Dunbar", SaveOnDunbar.getDistrict());
        assertEquals("University Endowment Lands", UBCFoodHub.getDistrict());
        assertEquals("Downtown", TomLeeGranville.getDistrict());
    }

    @Test
    void testGetDistanceFromHome(){
        assertEquals(1, SaveOnDunbar.getDistanceFromHome());
        assertEquals(7, UBCFoodHub.getDistanceFromHome());
        assertEquals(10, TomLeeGranville.getDistanceFromHome());
    }

    @Test
    void testSetName(){
        SaveOnDunbar.setName("Sample Name");
        assertEquals("Sample Name", SaveOnDunbar.getName());
        UBCFoodHub.setName("Name Example #2");
        assertEquals("Name Example #2", UBCFoodHub.getName());
    }

    @Test
    void testSetDistrict() {
        UBCFoodHub.setDistrict("Dunbar");
        assertEquals("Dunbar", UBCFoodHub.getDistrict());
        TomLeeGranville.setDistrict("Kerrisdale");
        assertEquals("Kerrisdale", TomLeeGranville.getDistrict());
    }

    @Test
    void testSetDistanceFromHome() {
        SaveOnDunbar.setDistanceFromHome(0);
        assertEquals(0, SaveOnDunbar.getDistanceFromHome());
        TomLeeGranville.setDistanceFromHome(400);
        assertEquals(400, TomLeeGranville.getDistanceFromHome());
    }
}
