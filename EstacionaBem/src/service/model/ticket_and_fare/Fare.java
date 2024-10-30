package service.model.ticket_and_fare;

import service.constants.Constants;

import java.io.Serializable;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public abstract class Fare implements Serializable {
    protected final Calendar ticketDate;
    protected Calendar paidDate;
    protected final Calendar limitDate;
    protected final Constants.VehicleType vehicleType;
    protected boolean paid;
    protected double value;

    public Fare(Calendar initialDate, Calendar limitDate, Constants.VehicleType vehicleType) {
        this.ticketDate = (Calendar) initialDate.clone();
        this.limitDate = (Calendar) limitDate.clone();
        this.vehicleType = vehicleType;
    }

    public double getValue() {
        return calculateFare();
    }

    public Calendar getPaidDate() {
        return paidDate;
    }

    public void payFare(){
        this.value = calculateFare();
        this.paidDate = Calendar.getInstance();
        this.paid = true;
    }

    public boolean isPaid() {
        return paid;
    }

    public abstract double calculateFare();


}
