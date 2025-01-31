package fr.cyu.depinfo.agp.tahiti.business.locations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    @Test
    void distanceParisNewYorkShouldBe5837200m() {
        Position paris= new Position(40.71339927688865,-74.00554206425352);
        Position newyork= new Position(48.85644492638115,2.3524421234662896) ;
        assertTrue(5837200 >= paris.distanceFrom(newyork)-100 && 5837200 <= paris.distanceFrom(newyork)+100);
    }
}