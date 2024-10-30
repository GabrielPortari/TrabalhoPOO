package service.model;

import service.constants.Constants;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private String color;
    private String model;
    private String licensePlate;
    private Constants.VehicleType vehicleType;

    public Vehicle(String color, String model, String licensePlate, Constants.VehicleType vehicleType) {
        this.color = color;
        this.model = model;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Constants.VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Constants.VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString(){
        return "Cor: " + getColor() + "\n" +
                "Modelo: " + getModel() + "\n" +
                "Placa: " + getLicensePlate() + "\n" +
                "Tipo: " + getVehicleType().getDescription() + "\n";
    }

}
