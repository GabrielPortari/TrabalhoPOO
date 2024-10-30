package service.repository;

import service.constants.Constants;
import service.database.ClientDatabase;
import service.model.Client;
import service.model.Vehicle;

import java.util.ArrayList;

public class ClientRepository {
    private final ClientDatabase clientDatabase = ClientDatabase.getInstance();

    public void insert(Client client){
        clientDatabase.insert(client);
    }

    public Client get(int document){
        return clientDatabase.get(document);
    }

    public void delete(Client c){
        clientDatabase.delete(c);
    }

    public void update(Client c, String newName){
        clientDatabase.update(c, newName);
    }

    public void update(Client c, int newDocument){
        clientDatabase.update(c, newDocument);
    }

    public ArrayList<Client> getAllClients(){
        return clientDatabase.getAllClients();
    }

    /*VEICULOS*/

    public void insertVehicle(Client c, Vehicle v){
        clientDatabase.insertVehicle(c, v);
    }

    public Vehicle getVehicle(String licensePlate){
        return clientDatabase.getVehicle(licensePlate);
    }

    public void deleteVehicle(Client c, Vehicle v){
        clientDatabase.deleteVehicle(c, v);
    }

    public void updateVehicle(Vehicle v, String newLicensePlateModelOrColor, Constants.VehicleUpdate operation){
        clientDatabase.updateVehicle(v, newLicensePlateModelOrColor, operation);

    }

    public void updateVehicle(Vehicle v, Constants.VehicleType newVehycleType){
        clientDatabase.updateVehicle(v, newVehycleType);
    }



}
