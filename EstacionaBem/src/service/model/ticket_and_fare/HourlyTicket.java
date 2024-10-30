package service.model.ticket_and_fare;

import service.constants.Constants;
import service.model.Client;
import service.model.ParkingSpace;
import service.model.Vehicle;

import java.io.Serializable;
import java.util.Calendar;

public class HourlyTicket extends Ticket implements Serializable {

    public HourlyTicket(Client client, Vehicle vehicle, ParkingSpace parkingSpace) {
        super(client, vehicle, parkingSpace);
        this.limitDate = setLimitDate();
        this.fare = new HourlyFare(this.initialDate, this.limitDate, this.vehicle.getVehicleType());
        this.type = Constants.ParkingLotTicketType.TYPE_HOURLY;
    }

    @Override
    protected Calendar setLimitDate() {
        Calendar limitDate = Calendar.getInstance();
        /*Seta o limite para meia noite do proximo dia*/
        limitDate.add(Calendar.DATE, 1);
        limitDate.set(Calendar.HOUR_OF_DAY, 0);
        limitDate.set(Calendar.MINUTE, 0);
        limitDate.set(Calendar.SECOND, 0);
        return limitDate;
    }
}
