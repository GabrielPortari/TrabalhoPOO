package service.database;

import service.constants.Constants;
import service.model.ParkingSpace;
import service.model.Vehicle;

import java.util.ArrayList;

public class ParkingSpaceDatabase {
    private static ParkingSpaceDatabase INSTANCE;
    private final ArrayList<ParkingSpace> parkingSpaceArrayList = new ArrayList<>();

    private ParkingSpaceDatabase(){};

    public static ParkingSpaceDatabase getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ParkingSpaceDatabase();
        }
        return INSTANCE;
    }

    public void insert(ParkingSpace ps){
        parkingSpaceArrayList.add(ps);
    }

    public ParkingSpace get(int number){
        ParkingSpace parkingSpace = null;
        for(ParkingSpace ps : parkingSpaceArrayList){
            if(ps.getNumber() == number){
                parkingSpace = ps;
            }
        }
        return parkingSpace;
    }

    public ParkingSpace get(Vehicle v){
        ParkingSpace parkingSpace = null;
        for (ParkingSpace p : parkingSpaceArrayList){
            if(p.getVehicle().getLicensePlate().equals(v.getLicensePlate())){
                parkingSpace = p;
            }
        }
        return parkingSpace;
    }

    public ArrayList<ParkingSpace> getAll(){
        return parkingSpaceArrayList;
    }

    public void delete(ParkingSpace ps){
        parkingSpaceArrayList.remove(ps);
    }

    public void update(ParkingSpace ps, int newNumber){
        ps.setNumber(newNumber);
    }

    public void update(ParkingSpace ps, String newStreet){
        ps.setStreet(newStreet);
    }

    public void update(ParkingSpace ps, Constants.VehicleType newVehicleType){
        ps.setVehicleType(newVehicleType);
    }

    public void changeAvailability(ParkingSpace ps, Constants.ParkingSpaceStatus availability) {
        if (availability == Constants.ParkingSpaceStatus.STATUS_AVAILABLE) {
            ps.setAvailable();
        }
        if (availability == Constants.ParkingSpaceStatus.STATUS_UNAVAILABLE) {
            ps.setUnavailable();
        }
    }

    public boolean verifyVehicleIsParked(Vehicle v){
        for(ParkingSpace ps : parkingSpaceArrayList){
            if(ps.getVehicle() == v){
                return true;
            }
        }
        return false;
    }



}
