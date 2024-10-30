package service.repository;

import service.constants.Constants;
import service.database.ParkingLotDatabase;
import service.model.Client;
import service.model.ParkingSpace;
import service.model.ticket_and_fare.Ticket;
import service.model.Vehicle;

import java.util.ArrayList;

public class ParkingLotRepository {
    ParkingLotDatabase parkingLotDatabase = ParkingLotDatabase.getInstance();

    public void insert(Ticket t){
        parkingLotDatabase.insert(t);
    }
    public void createTicket(Client c, Vehicle v, ParkingSpace ps, Constants.ParkingLotTicketType ticketType){
        parkingLotDatabase.createTicket(c, v, ps, ticketType);
    }

    public Ticket getTicket(Client c, Vehicle v){
        return parkingLotDatabase.getTicket(c, v);
    }
    public void payFare(Ticket t){
        parkingLotDatabase.payFare(t);
    }
    public void endTicket(Ticket t){
        parkingLotDatabase.endTicket(t);
    }
    public ArrayList<Ticket> getAll(){
        return parkingLotDatabase.getAll();
    }
}
