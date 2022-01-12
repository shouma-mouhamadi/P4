package com.parkit.parkingsystem.model;

import java.util.Calendar;
import java.util.Date;

public class Ticket {
    private int id;
    private ParkingSpot parkingSpot;
    private String vehicleRegNumber;
    private double price;
    private Date inTime;
    private Date outTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getInTime() {
        // Une méthode peut exposer sa représentation interne en renvoyant une référence à un objet modifiable
        Date inTime_copy = this.inTime;
        return inTime_copy;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        // Une méthode peut exposer sa représentation interne en renvoyant une référence à un objet modifiable
        Date outTime_copy = this.outTime;
        return outTime_copy;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public void print(){
        System.out.println("TICKET_ID : " + this.getId());
        System.out.println("PARKING_NUMBER : " + this.getParkingSpot().getId());
        System.out.println("VEHICLE_REG_NUMBER : " + this.getVehicleRegNumber());
        System.out.println("PRICE : " + this.getPrice() + "€");
        System.out.println("IN_TIME : " + this.getInTime());
        System.out.println("OUT_TIME : " + this.getOutTime());
        System.out.println("PARKING_TYPE : " + this.getParkingSpot().getParkingType());
    }
}
