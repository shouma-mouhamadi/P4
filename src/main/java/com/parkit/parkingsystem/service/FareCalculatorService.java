package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

import java.util.Calendar;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect : " + ticket.getOutTime().toString());
        }
        Calendar inHour = Calendar.getInstance();
        Calendar outHour = Calendar.getInstance();
        inHour.setTime(ticket.getInTime());
        outHour.setTime(ticket.getOutTime());
        long diff = outHour.getTimeInMillis() - inHour.getTimeInMillis(); // calculation of the parking time
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long durationSeconds = (diffDays*24*60*60)+(diffHours*60*60)+(diffMinutes*60)+(diffSeconds+1);
        long durationMinutes = durationSeconds/60;
        double parkingType; // calculation of the rate according to the type of vehicle
        switch (ticket.getParkingSpot().getParkingType()) {
            case CAR: {
                parkingType = Fare.CAR_RATE_PER_HOUR;
                break;
            }
            case BIKE: {
                parkingType = Fare.BIKE_RATE_PER_HOUR;
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown Parking Type");
        }
        ticket.setPrice((durationMinutes/60.0)*parkingType);
        double price = Math.round(ticket.getPrice()*100.0)/100.0; // we round the price to the nearest tenth of a cent
        if(durationMinutes <= 30){ // the first 30 minutes of parking are free
            price=0;
        }
        ticket.setPrice(price);
        System.out.println("PARKING_TIME : "+durationMinutes+" minutes");
    }
}