package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

import java.util.Calendar;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect because " + ticket.getOutTime().toString() + " != " + ticket.getInTime().toString());
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
        long durationSeconds = (diffDays*24*60*60)+(diffHours*60*60)+(diffMinutes*60)+(diffSeconds+1);
        long durationMinutes = durationSeconds/60;
        double parkingType; // calcul du tarif selon le type de vehicule

        System.out.println("Durée de stationnement : "+durationMinutes+" minutes");

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
            TicketDAO ticketDAO = new TicketDAO();
            ticket.setPrice((durationMinutes/60.0)*parkingType);

            if(ticketDAO.userIsRecurrent(ticket.getVehicleRegNumber())<2){ // l'utilisateur n'est pas récurrent
                double price = Math.round(ticket.getPrice()*100.0)/100.0; // on arrondi le prix au dixième de centimes près
                ticket.setPrice(price);
            }
            else{ // l'utilisateur est récurrent
                double fare = ticket.getPrice() - ((5*ticket.getPrice())/100); // on applique la réduction de 5%
                double price = Math.round(fare*100.0)/100.0; // on arrondi le prix au dixième de centimes près
                ticket.setPrice(price);
            }


        }

    }
}