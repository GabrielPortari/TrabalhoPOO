package service.helper;

import service.model.Client;
import service.model.ParkingSpace;
import service.model.ticket_and_fare.Ticket;
import service.repository.ClientRepository;
import service.repository.ParkingLotRepository;
import service.repository.ParkingSpaceRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeHelper {
    ClientRepository clientRepository = new ClientRepository();
    ParkingSpaceRepository parkingSpaceRepository = new ParkingSpaceRepository();
    ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

    private static final String CLIENT_ARCHIVE = "clients.ser";
    private static final String PARKING_SPACE_ARCHIVE = "parkingspaces.ser";
    private static final String TICKETS_ARCHIVE = "tickets.ser";
    public void saveSystemData(){
        serializeClients();
        serializeParkingSpaces();
        serializeTickets();
    }

    private void serializeClients(){
        ArrayList<Client> clients = clientRepository.getAllClients();
        try {
            ObjectOutputStream clientsOutput = new ObjectOutputStream(new FileOutputStream(CLIENT_ARCHIVE));
            clientsOutput.writeObject(clients);
            clientsOutput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void serializeParkingSpaces(){
        ArrayList<ParkingSpace> parkingSpaces = parkingSpaceRepository.getAll();
        try {
            ObjectOutputStream parkingSpacesOutput = new ObjectOutputStream(new FileOutputStream(PARKING_SPACE_ARCHIVE));
            parkingSpacesOutput.writeObject(parkingSpaces);
            parkingSpacesOutput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void serializeTickets(){
        ArrayList<Ticket> tickets = parkingLotRepository.getAll();
        try {
            ObjectOutputStream ticketsOutput = new ObjectOutputStream(new FileOutputStream(TICKETS_ARCHIVE));
            ticketsOutput.writeObject(tickets);
            ticketsOutput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadSystemData(){
        loadClients();
        loadParkingSpaces();
        loadTickets();
    }

    private void loadClients(){
        List<Client> clients = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(CLIENT_ARCHIVE);
             ObjectInputStream ois = new ObjectInputStream(fis)){

            while (true) {
                try {
                    List<Client> c = (List<Client>) ois.readObject();
                    clients.addAll(c);
                }catch (EOFException e){
                    //Fim do arquivo
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(Client client : clients){
            clientRepository.insert(client);
        }
    }

    private void loadParkingSpaces(){
        List<ParkingSpace> parkingSpaces = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(PARKING_SPACE_ARCHIVE);
             ObjectInputStream ois = new ObjectInputStream(fis)){

            while (true) {
                try {
                    List<ParkingSpace> ps = (List<ParkingSpace>) ois.readObject();
                    parkingSpaces.addAll(ps);
                }catch (EOFException e){
                    //Fim do arquivo
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(ParkingSpace ps : parkingSpaces){
            parkingSpaceRepository.insert(ps);
        }
    }

    private void loadTickets(){
        List<Ticket> tickets = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(TICKETS_ARCHIVE);
             ObjectInputStream ois = new ObjectInputStream(fis)){

            while (true) {
                try {
                    List<Ticket> ps = (List<Ticket>) ois.readObject();
                    tickets.addAll(ps);
                }catch (EOFException e){
                    //Fim do arquivo
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(Ticket t : tickets){
            parkingLotRepository.insert(t);
        }
    }
}
