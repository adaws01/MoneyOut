package model;

import model.moneyoutprimitives.Location;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    private Location SaveOnDunbar = Location.accessSaveOnDunbar();
    private static Location UBCFoodHub;
    private static Location TomLeeGranville;

    @BeforeAll
    static void runBefore() {
        UBCFoodHub = new Location("UBC Food Hub Market", "University Endowment Lands", 7);
        TomLeeGranville = new Location("Tom Lee", "Downtown", 10);
    }

    @Test
    void testGetName(){
        assertEquals("Save On Foods", SaveOnDunbar.getName());
        assertEquals("UBC Food Hub Market", UBCFoodHub.getName());
        assertEquals("Tom Lee", TomLeeGranville.getName());
    }

    @Test
    void testGetDistrict(){
        assertEquals("Dunbar", SaveOnDunbar.getDistrict());
        assertEquals("Dunbar", UBCFoodHub.getDistrict());
        assertEquals("Kerrisdale", TomLeeGranville.getDistrict());
    }

    @Test
    void testGetDistanceFromHome(){
        assertEquals(1, SaveOnDunbar.getDistanceFromHome());
        assertEquals(7, UBCFoodHub.getDistanceFromHome());
        assertEquals(400, TomLeeGranville.getDistanceFromHome());
    }

    @Test
    void testSetName(){
        TomLeeGranville.setName("Sample Name");
        assertEquals("Sample Name", TomLeeGranville.getName());
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
        TomLeeGranville.setDistanceFromHome(0);
        assertEquals(0, TomLeeGranville.getDistanceFromHome());
        TomLeeGranville.setDistanceFromHome(400);
        assertEquals(400, TomLeeGranville.getDistanceFromHome());
    }

    @Test
    void testAccessLocationList() {
        assertTrue(Location.accessLocationList().size() > 0);
    }
}
