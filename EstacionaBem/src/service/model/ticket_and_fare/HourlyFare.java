package service.model.ticket_and_fare;

import service.constants.Constants;

import java.io.Serializable;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class HourlyFare extends Fare implements Serializable {

    private final double firstHourValue;
    private final double subsequentHourValue;
    protected final int weekDay;

    public HourlyFare(Calendar initialDate, Calendar limitDate, Constants.VehicleType vehicleType) {
        super(initialDate, limitDate, vehicleType);
        this.weekDay = ticketDate.get(Calendar.DAY_OF_WEEK);
        this.firstHourValue = getFirstHourValue();
        this.subsequentHourValue = getSubsequentHourValue();
    }

    @Override
    public double calculateFare() {
        Calendar actualDate = Calendar.getInstance();
        long timePassed = TimeUnit.MILLISECONDS.toSeconds(actualDate.getTimeInMillis() - this.ticketDate.getTimeInMillis());
        return vehicleType.getTaxValue() * (calculateFine() + this.firstHourValue + (this.subsequentHourValue*Math.floor(timePassed/3600)));
    }

    public double calculateFine(){
        Calendar actualDate = Calendar.getInstance();
        if(this.limitDate.compareTo(actualDate) < 0){
            long timePassed = TimeUnit.MILLISECONDS.toSeconds(actualDate.getTimeInMillis() - this.limitDate.getTimeInMillis());
            System.out.println("FINE " + Math.ceil(timePassed/3600) + "");
            System.out.println("TIME PASSED SEC " + timePassed + "");
            return firstHourValue*(Math.ceil(timePassed/3600));
        }else {
            return 0;
        }
    }

    protected double getFirstHourValue(){
        switch (weekDay){
            case Calendar.SUNDAY -> {
                return Constants.ParkingLotValues.PRICE_FIRST_HOUR_SUNDAY.getValue();
            }
            case Calendar.MONDAY -> {
                return Constants.ParkingLotValues.PRICE_FIRST_HOUR_MONDAY.getValue();
            }
            case Calendar.TUESDAY -> {
                return Constants.ParkingLotValues.PRICE_FIRST_HOUR_TUESDAY.getValue();
            }
            case Calendar.WEDNESDAY -> {
                return Constants.ParkingLotValues.PRICE_FIRST_HOUR_WEDNESDAY.getValue();
            }
            case Calendar.THURSDAY -> {
                return Constants.ParkingLotValues.PRICE_FIRST_HOUR_THURSDAY.getValue();
            }
            case Calendar.FRIDAY -> {
                return Constants.ParkingLotValues.PRICE_FIRST_HOUR_FRIDAY.getValue();
            }
            case Calendar.SATURDAY -> {
                return Constants.ParkingLotValues.PRICE_FIRST_HOUR_SATURDAY.getValue();
            }
        }
        return 1;
    }

    protected double getSubsequentHourValue(){
        switch (weekDay){
            case Calendar.SUNDAY -> {
                return Constants.ParkingLotValues.PRICE_SUBSEQUENT_HOUR_SUNDAY.getValue();
            }
            case Calendar.MONDAY -> {
                return Constants.ParkingLotValues.PRICE_SUBSEQUENT_HOUR_MONDAY.getValue();
            }
            case Calendar.TUESDAY -> {
                return Constants.ParkingLotValues.PRICE_SUBSEQUENT_HOUR_TUESDAY.getValue();
            }
            case Calendar.WEDNESDAY -> {
                return Constants.ParkingLotValues.PRICE_SUBSEQUENT_HOUR_WEDNESDAY.getValue();
            }
            case Calendar.THURSDAY -> {
                return Constants.ParkingLotValues.PRICE_SUBSEQUENT_HOUR_THURSDAY.getValue();
            }
            case Calendar.FRIDAY -> {
                return Constants.ParkingLotValues.PRICE_SUBSEQUENT_HOUR_FRIDAY.getValue();
            }
            case Calendar.SATURDAY -> {
                return Constants.ParkingLotValues.PRICE_SUBSEQUENT_HOUR_SATURDAY.getValue();
            }
        }
        return 1;
    }

}
