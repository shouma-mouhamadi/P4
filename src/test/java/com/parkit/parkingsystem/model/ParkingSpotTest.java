package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotTest {

    @Test
    void setId() {
        ParkingSpot parkingSpot;
        parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
        parkingSpot.setId(2);
        assertEquals(2,parkingSpot.getId());
    }

    @Test
    void setParkingType() {
        ParkingSpot parkingSpot;
        parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
        parkingSpot.setParkingType(ParkingType.BIKE);
        assertEquals(ParkingType.BIKE,parkingSpot.getParkingType());
    }

    @Test
    void testEquals() {
        ParkingSpot parkingSpot1 = new ParkingSpot(1, ParkingType.CAR,false);
        ParkingSpot parkingSpot2 = new ParkingSpot(1, ParkingType.CAR,false);
        boolean parkingSpot3 = parkingSpot1.equals(parkingSpot2);
        assertTrue(parkingSpot3);

    }

    @Test
    void testHashCode() {
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
        int parkingSpot2 = parkingSpot.hashCode();
        assertEquals(1,parkingSpot2);
    }
}