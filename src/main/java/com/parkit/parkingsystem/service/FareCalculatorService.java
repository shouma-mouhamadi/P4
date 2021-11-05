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

        long diff = outHour.getTimeInMillis() - inHour.getTimeInMillis(); // calcul de la durée de stationnement
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long durationMinutes = (diffDays * 24 * 60) + (diffHours * 60) + (diffMinutes);

        double parkingType; // calcul du tarif selon le type de vehicule
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
                throw new IllegalArgumentException("Unknow Parking Type");
        }

        if(durationMinutes <= 30){ // les 30 premières minutes de stationnement sont gratuites
            ticket.setPrice(0);
        }else {
            ticket.setPrice( (diffDays * 24 * parkingType) + (diffHours * parkingType) + (diffMinutes * (parkingType/60)) + (diffSeconds * (parkingType/60*60)) );
        }
        double price = Math.round(ticket.getPrice()*100.0)/100.0; // on arrondi le prix au dixième de centimes près

        /*System.out.println("PARKING TICKET");
        System.out.print("Parking lot number : ");
        System.out.println(ticket.getParkingSpot().getId());
        System.out.print("Parking Type : ");
        System.out.println(ticket.getParkingSpot().getParkingType());
        System.out.print("Vehicule registration number : ");
        System.out.println(ticket.getVehicleRegNumber());
        System.out.print("Arrival : ");
        System.out.println(ticket.getInTime());
        System.out.print("Departure : ");
        System.out.println(ticket.getOutTime());
        System.out.print("Duration : ");
        System.out.print(diffDays + " days, ");
        System.out.print(diffHours + " hours, ");
        System.out.print(diffMinutes + " minutes, ");
        System.out.println(diffSeconds + " seconds");
        System.out.println("Fare : " + price + "$\n");*/

    }
}