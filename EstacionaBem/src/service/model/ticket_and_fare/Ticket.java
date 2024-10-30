package service.model.ticket_and_fare;

import service.constants.Constants;
import service.model.Client;
import service.model.ParkingSpace;
import service.model.Vehicle;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Ticket implements Serializable {
    protected Calendar initialDate;
    protected Calendar limitDate;
    protected Calendar endDate;
    protected boolean ended;
    protected Client client;
    protected Vehicle vehicle;
    protected ParkingSpace parkingSpace;
    protected Fare fare;
    protected Constants.ParkingLotTicketType type;

    public Ticket(Client client, Vehicle vehicle, ParkingSpace parkingSpace) {
        this.initialDate = Calendar.getInstance();
        this.client = client;
        this.vehicle = vehicle;
        this.parkingSpace = parkingSpace;
        this.ended = false;
    }

    public Client getClient() {
        return client;
    }

    public Constants.ParkingLotTicketType getType() {
        return type;
    }

    public boolean isExpired(){
        Calendar actualDate = Calendar.getInstance();
        return actualDate.after(this.limitDate);
    }

    protected abstract Calendar setLimitDate();

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public boolean isEnded() {
        return ended;
    }

    public void payFare(){
        this.fare.payFare();
    }

    public Fare getFare() {
        return fare;
    }

    public void endTicket(){
        this.endDate = Calendar.getInstance();
        this.ended = true;
    }

}
