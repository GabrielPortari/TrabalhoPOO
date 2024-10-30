package service.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable {
    private String name;
    private int document;
    private ArrayList<Vehicle> vehicles;

    public Client(String name, int document) {
        this.name = name;
        this.document = document;
        this.vehicles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle getVehicle(String licensePlate){
        Vehicle vehicle = null;
        for(Vehicle v : this.vehicles){
            if(v.getLicensePlate().equals(licensePlate)){
                vehicle = v;
            }
        }
        return vehicle;
    }

    @Override
    public String toString(){
        return "Nome: " + getName() + "\nN° de documento: " + getDocument() + ".";
    }
}
