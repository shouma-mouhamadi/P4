package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;
import org.junit.jupiter.api.Test;

import java.util.Date;

class TicketTest {

    @Test
    void print() {
        String vehicleRegNumber = "ABCDEF";
        ParkingType parkingType = ParkingType.CAR;
        ParkingSpot parkingSpot = new ParkingSpot(1, parkingType,false);
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicleRegNumber(vehicleRegNumber);
        ticket.setPrice(2);
        ticket.setInTime(new Date(System.currentTimeMillis()-1000*60*60));
        ticket.setOutTime(new Date());
        ticket.print();
    }
}