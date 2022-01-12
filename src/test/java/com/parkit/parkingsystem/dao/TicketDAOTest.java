package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Date;

class TicketDAOTest {


    @Test
    void saveTicketTest(){
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setParkingSpot(parkingSpot);
        ticket.setInTime(new Date(System.currentTimeMillis() - 1000*60*60));
        ticket.setOutTime(new Date());
        ticket.setPrice(0);
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.saveTicket(ticket);
    }

    @Test
    void getTicketTest() {
        String vehicleRegNumber = "ABCDEF";
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.getTicket(vehicleRegNumber);
    }

    @Test
    void updateTicketTest() {
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setParkingSpot(parkingSpot);
        ticket.setInTime(new Date(System.currentTimeMillis() - 1000*60*60));
        ticket.setOutTime(new Date());
        ticket.setPrice(0);
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.updateTicket(ticket);
    }

    @Test
    void generateTicketTest() {
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setParkingSpot(parkingSpot);
        ticket.setInTime(new Date(System.currentTimeMillis() - 1000*60*60));
        ticket.setOutTime(new Date());
        ticket.setPrice(0);
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.generateTicket(ticket);
    }

    @Test
    void userIsRecurrentTest() {
        String vehicleRegNumber = "ABCDEF";
        TicketDAO ticketDAO = new TicketDAO();
        ticketDAO.userIsRecurrent(vehicleRegNumber);
    }
}