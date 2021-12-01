package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParkingDataBaseIT {

    private static final DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;

    @Mock
    private static InputReaderUtil inputReaderUtil;

    @BeforeAll
    private static void setUp() {
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
    }

    @BeforeEach
    private void setUpPerTest() throws Exception {
        when(inputReaderUtil.readSelection()).thenReturn(1);
        when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("GHIJKL");
        dataBasePrepareService.clearDataBaseEntries();
    }

    @AfterAll
    private static void tearDown(){
    }

    @Test
    public void testParkingACar() throws Exception {

        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        parkingService.processIncomingVehicle();

        Ticket ticket = ticketDAO.getTicket(inputReaderUtil.readVehicleRegistrationNumber());
        assertEquals(inputReaderUtil.readVehicleRegistrationNumber() , ticket.getVehicleRegNumber()); // check that a ticket is actualy saved in DB
        assertFalse(ticket.getParkingSpot().isAvailable()); // Parking table is updated with availability

        ticket.print();
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        System.out.println(parkingSpotDAO.getParkingSpotAvailability(parkingSpot));


    }

    @Test
    public void testParkingLotExit() throws Exception {
        testParkingACar();

        Ticket ticket = ticketDAO.getTicket(inputReaderUtil.readVehicleRegistrationNumber());
        Date inTime = new Date(System.currentTimeMillis()-(1000*60*120));
        ticket.setInTime(inTime);
        ticketDAO.generateTicketTest(ticket);

        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        parkingService.processExitingVehicle();

        Ticket ticket2 = ticketDAO.getTicket(inputReaderUtil.readVehicleRegistrationNumber());
        assertEquals(inputReaderUtil.readVehicleRegistrationNumber() , ticket2.getVehicleRegNumber()); // check that a ticket is actualy saved in DB

        ticket2.print();

        ParkingSpot parkingSpot = ticket2.getParkingSpot();
        parkingSpotDAO.getParkingSpotAvailability(parkingSpot);

        //TODO: check that the fare generated and out time are populated correctly in the database
    }
    

 
}
