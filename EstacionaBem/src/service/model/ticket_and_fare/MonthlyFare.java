package service.model.ticket_and_fare;

import service.constants.Constants;

import java.io.Serializable;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MonthlyFare extends Fare implements Serializable {
    private final double monthlyPrice;

    public MonthlyFare(Calendar initialDate, Calendar limitDate, Constants.VehicleType vehicleType) {
        super(initialDate, limitDate, vehicleType);
        this.monthlyPrice = Constants.ParkingLotValues.PRICE_MONTHLY.getValue();
    }

    @Override
    public double calculateFare() {
        return vehicleType.getTaxValue() * monthlyPrice;
    }
}
