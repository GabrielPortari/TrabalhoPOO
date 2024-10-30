package service.database;

import service.constants.Constants;
import service.model.*;
import service.model.ticket_and_fare.HourlyTicket;
import service.model.ticket_and_fare.MonthlyTicket;
import service.model.ticket_and_fare.Ticket;

import java.util.ArrayList;

public class ParkingLotDatabase {

    private static ParkingLotDatabase INSTANCE;

    private final ArrayList<Ticket> ticketArrayList = new ArrayList<>();

    private ParkingLotDatabase(){}

    public static ParkingLotDatabase getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ParkingLotDatabase();
        }
        return INSTANCE;
    }

    public void insert(Ticket t){
        ticketArrayList.add(t);
    }
    public void createTicket(Client c, Vehicle v, ParkingSpace ps, Constants.ParkingLotTicketType ticketType){
        switch (ticketType){
            case TYPE_HOURLY -> {
                HourlyTicket hourlyTicket = new HourlyTicket(c, v, ps);
                ticketArrayList.add(hourlyTicket);
            }
            case TYPE_MONTHLY -> {
                MonthlyTicket monthlyTicket = new MonthlyTicket(c, v, ps);
                ticketArrayList.add(monthlyTicket);
            }
        }
    }

    public void payFare(Ticket t){
        t.payFare();
    }

    public void endTicket(Ticket t) {
        t.endTicket();
    }

    public Ticket getTicket(Client c, Vehicle v){
        Ticket ticket = null;
        for(Ticket t : ticketArrayList){
            if(t.getClient().getDocument() == c.getDocument() && t.getVehicle().getLicensePlate().equals(v.getLicensePlate()) && !t.isEnded()){
                ticket = t;
            }
        }
        return ticket;
    }

    public ArrayList<Ticket> getAll(){
        return ticketArrayList;
    }
}
