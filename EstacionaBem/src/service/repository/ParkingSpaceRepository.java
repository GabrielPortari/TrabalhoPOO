package service.repository;

import service.constants.Constants;
import service.database.ParkingSpaceDatabase;
import service.model.ParkingSpace;
import service.model.Vehicle;

import java.util.ArrayList;

public class ParkingSpaceRepository {
    private final ParkingSpaceDatabase parkingSpaceDatabase = ParkingSpaceDatabase.getInstance();

    public void insert(ParkingSpace ps){
        parkingSpaceDatabase.insert(ps);
    }

    public ParkingSpace get(int number){
        return parkingSpaceDatabase.get(number);
    }
    public ParkingSpace get(Vehicle v){
        return parkingSpaceDatabase.get(v);
    }

    public ArrayList<ParkingSpace> getAll(){
        return parkingSpaceDatabase.getAll();
    }

    public void delete(ParkingSpace ps){
        parkingSpaceDatabase.delete(ps);
    }

    public void update(ParkingSpace ps, int newNumber){
        parkingSpaceDatabase.update(ps, newNumber);
    }

    public void update(ParkingSpace ps, String newStreet){
        parkingSpaceDatabase.update(ps, newStreet);
    }

    public void update(ParkingSpace ps, Constants.VehicleType newVehicleType){
        parkingSpaceDatabase.update(ps, newVehicleType);
    }

    public void changeAvailability(ParkingSpace ps, Constants.ParkingSpaceStatus status){
        parkingSpaceDatabase.changeAvailability(ps, status);
    }

    public boolean verifyVehicleIsParked(Vehicle v){
        return parkingSpaceDatabase.verifyVehicleIsParked(v);
    }



}
