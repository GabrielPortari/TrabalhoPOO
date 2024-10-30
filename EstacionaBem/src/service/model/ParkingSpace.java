package service.model;

import service.constants.Constants;

import java.io.Serializable;

public class ParkingSpace implements Serializable {
    private int number;
    private String street;
    private Constants.VehicleType vehicleType;
    private Constants.ParkingSpaceStatus status;
    private Vehicle vehicle;

    public ParkingSpace(int number, String street, Constants.VehicleType vehicleType) {
        this.number = number;
        this.street = street;
        this.vehicleType = vehicleType;
        this.status = Constants.ParkingSpaceStatus.STATUS_AVAILABLE;
        this.vehicle = null;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Constants.VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Constants.VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Constants.ParkingSpaceStatus getStatus() {
        return status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void fillSpace(Vehicle v){
        setVehicle(v);
        setBusy();
    }

    public void emptySpace(){
        setVehicle(null);
        setAvailable();
    }

    public void setUnavailable() {
        this.status = Constants.ParkingSpaceStatus.STATUS_UNAVAILABLE;
    }
    public void setAvailable() {
        this.status = Constants.ParkingSpaceStatus.STATUS_AVAILABLE;
    }
    public void setBusy() {
        this.status = Constants.ParkingSpaceStatus.STATUS_BUSY;
    }

    @Override
    public String toString(){
        return  "Número: " + getNumber() + "\n" +
                "Rua: " + getStreet() + "\n" +
                "Disponibilidade: " + getStatus().getDescription() + "\n" +
                "Tipo de veículo: " + getVehicleType().getDescription() + "\n";
    }
}
