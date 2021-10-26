package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;
import java.util.Calendar;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }
        Calendar inHour = Calendar.getInstance();
        Calendar outHour = Calendar.getInstance();
        inHour.setTime(ticket.getInTime());
        outHour.setTime(ticket.getOutTime());
        float durationInDays = outHour.get(Calendar.DAY_OF_MONTH) - inHour.get(Calendar.DAY_OF_MONTH);
        float durationInHours = outHour.get(Calendar.HOUR_OF_DAY) - inHour.get(Calendar.HOUR_OF_DAY) + (24 * durationInDays);
        float durationInMinutes = outHour.get(Calendar.MINUTE) - inHour.get(Calendar.MINUTE) + (60 * durationInHours) ;


        if(durationInMinutes<60){ // Moins d'une heure de stationnement
            durationInHours=0;
        }else {
            durationInMinutes-=60; // Plus d'une heure de stationnement
        }



        // calcul du tarif selon le vehicule
        switch (ticket.getParkingSpot().getParkingType()) {
            case CAR: {
                if(durationInHours>0) {
                    ticket.setPrice(durationInHours * Fare.CAR_RATE_PER_HOUR);
                }else {
                    if(durationInMinutes<=30){
                        ticket.setPrice(0);
                    }else {
                        ticket.setPrice((durationInMinutes/60) * Fare.CAR_RATE_PER_HOUR);
                    }
                }
                break;
            }
            case BIKE: {
                if(durationInHours>0) {
                    ticket.setPrice(durationInHours * Fare.BIKE_RATE_PER_HOUR);
                }else {
                    if(durationInMinutes<=30){
                        ticket.setPrice(0);
                    }else {
                        ticket.setPrice((durationInMinutes/60) * Fare.BIKE_RATE_PER_HOUR);
                    }
                }
                break;
            }
            default:
                throw new IllegalArgumentException("Unknow Parking Type");
        }
        System.out.println("Début du stationnement : " + ticket.getInTime());
        System.out.println("Fin du stationnement : " + ticket.getOutTime());
        System.out.println("Durée : " + durationInHours + " Heure(s) et " + durationInMinutes + " Minute(s)");
        System.out.println("Prix : " + ticket.getPrice() + "€");
    }
}