package menu.menu_operations;

import service.model.Client;
import service.model.Vehicle;
import service.model.menu_interfaces.UserOperations;
import service.model.ticket_and_fare.Ticket;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ClientOperations extends UserOperations {


    protected final String clientNotExists = "Este cliente não existe.";

    public void insert(int operationType) {
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual n° de documento do cliente?", operationType));

            if (clientRepository.get(clientDocument) == null) {
                String clientName = inputScannerHelper.scanInput("Qual nome do cliente?", operationType);
                Client c = new Client(clientName, clientDocument);
                clientRepository.insert(c);
                messageHelper.showMessage("Cliente " + c.getName() + " inserido no sistema com sucesso.", operationType);
            } else {
                messageHelper.showMessage("Este cliente já existe.", operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    public void get(int operationType) {
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual n° de documento do cliente?", operationType));
            Client c = clientRepository.get(clientDocument);
            if (c != null) {
                messageHelper.showMessage(c.toString(), operationType);
            } else {
                messageHelper.showMessage(clientNotExists, operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    public void update(int operationType) {
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual n° de documento do cliente?", operationType));
            Client c = clientRepository.get(clientDocument);
            if (c != null) {

                ArrayList<Vehicle> clientVehicles = c.getVehicles();
                boolean ticketExists = false;
                for(Vehicle v : clientVehicles){
                    if(parkingLotRepository.getTicket(c, v) != null){
                        ticketExists = true;
                    }
                }
                if(!ticketExists) {
                    List<String> menuItems = new ArrayList<>();
                    menuItems.add("1 - Alterar nome");
                    menuItems.add("2 - Alterar n° de documento");
                    int updateType = menuItemHelper.getMenuItem(menuItems, operationType);
                    switch (updateType) {
                        case 1 -> {
                            String newName = inputScannerHelper.scanInput("Qual o novo nome do cliente?", operationType);
                            clientRepository.update(c, newName);
                            messageHelper.showMessage("Nome alterado com sucesso.", operationType);
                        }
                        case 2 -> {
                            int newDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual o novo n° de documento do cliente?", operationType));
                            if (clientRepository.get(newDocument) == null) {
                                clientRepository.update(c, newDocument);
                                messageHelper.showMessage("Documento alterado com sucesso.", operationType);
                            } else {
                                messageHelper.showMessage("Este documento já está cadastrado.", operationType);
                            }
                        }
                        default -> messageHelper.showMessage("Você selecionou uma opção inválida.", operationType);
                    }
                }else{
                    messageHelper.showMessage("Você não pode alterar um cliente com ticket em aberto.", operationType);
                }
            } else {
                messageHelper.showMessage(clientNotExists, operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    public void delete(int operationType) {
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual n° de documento do cliente?", operationType));
            Client c = clientRepository.get(clientDocument);
            if (c != null) {

                ArrayList<Vehicle> clientVehicles = c.getVehicles();
                boolean ticketExists = false;
                for (Vehicle v : clientVehicles) {
                    if (parkingLotRepository.getTicket(c, v) != null) {
                        ticketExists = true;
                    }
                }

                if (!ticketExists) {
                    clientRepository.delete(c);
                    messageHelper.showMessage("Cliente excluido com sucesso.", operationType);
                } else {
                    messageHelper.showMessage("Você não pode excluir um cliente com ticket em aberto.", operationType);
                }
            }else{
                messageHelper.showMessage(clientNotExists, operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    public void getAllClients(int operationType){
        ArrayList<Client> clients = clientRepository.getAllClients();
        StringBuilder s = new StringBuilder();
        for(Client c : clients){
            s.append("Nome: ").append(c.getName()).append("\nDocumento: ").append(c.getDocument()).append("\n\n");
        }
        messageHelper.showMessage(s.toString(), operationType);
    }
}
