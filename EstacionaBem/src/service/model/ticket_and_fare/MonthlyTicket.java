package service.model.ticket_and_fare;

import service.constants.Constants;
import service.model.Client;
import service.model.ParkingSpace;
import service.model.Vehicle;

import java.io.Serializable;
import java.util.Calendar;

public class MonthlyTicket extends Ticket implements Serializable {
    public MonthlyTicket(Client client, Vehicle vehicle, ParkingSpace parkingSpace) {
        super(client, vehicle, parkingSpace);
        this.limitDate = setLimitDate();
        this.fare = new MonthlyFare(this.initialDate, this.limitDate, this.vehicle.getVehicleType());
        this.type = Constants.ParkingLotTicketType.TYPE_MONTHLY;
    }

    @Override
    protected Calendar setLimitDate() {
        /*Seta o limite para 30 dias após a criação do ticket*/
        Calendar limitDate = (Calendar) this.initialDate.clone();
        limitDate.add(Calendar.DATE, 30);
        return limitDate;
    }



}
