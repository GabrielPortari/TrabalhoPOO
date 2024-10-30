package service.constants;

import java.util.Calendar;

public class Constants {
    public static class ExecutionType{
        public static final int TYPE_TERMINAL = 1;
        public static final int TYPE_GRAPHIC = 2;
    }

    public enum VehicleUpdate{
        UPDATE_LICENSE_PLATE, UPDATE_MODEL, UPDATE_COLOR;
    }

    public enum VehicleType{
        TYPE_MOTORCYCLE(0.5, "Moto"), TYPE_CAR(1, "Carro"), TYPE_BUS(2, "Onibus");

        private final double taxValue;
        private final String description;

        VehicleType(double value, String description){
            this.taxValue = value;
            this.description = description;
        }

        public double getTaxValue(){return taxValue;}
        public String getDescription(){return description;}
    }

    public enum ParkingSpaceStatus{
        STATUS_AVAILABLE("Disponível"), STATUS_BUSY("Ocupada"), STATUS_UNAVAILABLE("Indisponível");

        private final String description;
        ParkingSpaceStatus(String description){
            this.description = description;
        }

        public String getDescription(){return description;}
    }

    public enum ParkingLotTicketType{
        TYPE_HOURLY(), TYPE_MONTHLY();
    }

    public enum ParkingLotValues{
        PRICE_MONTHLY(Calendar.MONTH, 100),

        PRICE_FIRST_HOUR_SUNDAY(Calendar.SUNDAY, 2.0),
        PRICE_FIRST_HOUR_MONDAY(Calendar.MONDAY, 3.0),
        PRICE_FIRST_HOUR_TUESDAY(Calendar.TUESDAY, 4.0),
        PRICE_FIRST_HOUR_WEDNESDAY(Calendar.WEDNESDAY, 5.0),
        PRICE_FIRST_HOUR_THURSDAY(Calendar.THURSDAY, 6.0),
        PRICE_FIRST_HOUR_FRIDAY(Calendar.FRIDAY, 7.0),
        PRICE_FIRST_HOUR_SATURDAY(Calendar.SATURDAY, 8.0),

        PRICE_SUBSEQUENT_HOUR_SUNDAY(Calendar.SUNDAY, 2.0),
        PRICE_SUBSEQUENT_HOUR_MONDAY(Calendar.MONDAY, 4.0),
        PRICE_SUBSEQUENT_HOUR_TUESDAY(Calendar.TUESDAY, 6.0),
        PRICE_SUBSEQUENT_HOUR_WEDNESDAY(Calendar.WEDNESDAY, 5.0),
        PRICE_SUBSEQUENT_HOUR_THURSDAY(Calendar.THURSDAY, 3.0),
        PRICE_SUBSEQUENT_HOUR_FRIDAY(Calendar.FRIDAY, 7.0),
        PRICE_SUBSEQUENT_HOUR_SATURDAY(Calendar.SATURDAY, 8.0);

        private final int dayOfWeek;
        private double value;

        ParkingLotValues(int dayOfWeek, double value){
            this.dayOfWeek = dayOfWeek;
            this.value = value;
        }

        public int getDayOfWeek(){
            return this.dayOfWeek;
        }

        public double getValue(){
            return this.value;
        }

        public void setValue(double value){
            this.value = value;
        }
    }

}
