package service.database;

import service.constants.Constants;
import service.model.Client;
import service.model.Vehicle;

import java.util.ArrayList;

public class ClientDatabase {
    private static ClientDatabase INSTANCE;

    private final ArrayList<Client> clientArrayList = new ArrayList<>();
    private ClientDatabase(){}

    public static ClientDatabase getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ClientDatabase();
        }
        return INSTANCE;
    }

    public void insert(Client c){
        clientArrayList.add(c);
    }

    public Client get(int document){
        Client client = null;
        for (Client c : clientArrayList){
            if(c.getDocument() == document){
                client = c;
            }
        }
        return client;
    }

    public void delete(Client c){
        clientArrayList.remove(c);
    }

    public void update(Client c, String newName){
        c.setName(newName);
    }

    public void update(Client c, int newDocument){
        c.setDocument(newDocument);
    }

    public ArrayList<Client> getAllClients (){
        return clientArrayList;
    }

    /* VEICULOS */

    public void insertVehicle(Client c, Vehicle v){
        c.getVehicles().add(v);
    }

    public Vehicle getVehicle(String licensePlate){
        Vehicle vehicle = null;

        for(Client c : clientArrayList){
            for(Vehicle v : c.getVehicles()){
                if(v.getLicensePlate().equals(licensePlate)){
                    vehicle = v;
                }
            }
        }
        return vehicle;
    }

    public void deleteVehicle(Client c, Vehicle v){
        c.getVehicles().remove(v);
    }

    public void updateVehicle(Vehicle v, String newLicensePlateModelOrColor, Constants.VehicleUpdate operation){

        switch (operation){
            case Constants.VehicleUpdate.UPDATE_LICENSE_PLATE:
                v.setLicensePlate(newLicensePlateModelOrColor);
                break;
            case Constants.VehicleUpdate.UPDATE_MODEL:
                v.setModel(newLicensePlateModelOrColor);
                break;
            case Constants.VehicleUpdate.UPDATE_COLOR:
                v.setColor(newLicensePlateModelOrColor);
                break;
        }
    }

    public void updateVehicle(Vehicle v, Constants.VehicleType newVehycleType){
        v.setVehicleType(newVehycleType);
    }

}
