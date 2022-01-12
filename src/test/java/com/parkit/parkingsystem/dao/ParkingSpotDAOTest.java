package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotDAOTest {

    @Test
    void getNextAvailableSlot() {
        ParkingType parkingType = ParkingType.CAR;
        ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.getNextAvailableSlot(parkingType);
    }

    @Test
    void getParkingSpotAvailability() {
        ParkingSpot parkingSpot = new ParkingSpot(1,ParkingType.CAR,false);
        ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.getParkingSpotAvailability(parkingSpot);
    }

    @Test
    void updateParking() {
        ParkingSpot parkingSpot = new ParkingSpot(1,ParkingType.CAR,false);
        ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.updateParking(parkingSpot);
    }
}