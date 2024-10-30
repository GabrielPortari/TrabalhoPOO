package menu.menu_operations;

import service.constants.Constants;
import service.model.Client;
import service.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleOperations extends ClientOperations{

    private final String vehicleNotExists = "Este veículo não existe";

    @Override
    public void insert(int operationType){
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual n° de documento do cliente?", operationType));
            Client c = clientRepository.get(clientDocument);
            if (c != null) {
                String vehiclePlate = inputScannerHelper.scanInput("Qual a placa do veiculo?", operationType);
                Vehicle v = clientRepository.getVehicle(vehiclePlate);
                if (v == null) {
                    String vehicleModel = inputScannerHelper.scanInput("Qual o modelo do veiculo?", operationType);
                    String vehicleColor = inputScannerHelper.scanInput("Qual a cor do veiculo?", operationType);

                    Constants.VehicleType vehicleType = getVehicleType(operationType);

                    Vehicle newVehicle = new Vehicle(vehicleColor, vehicleModel, vehiclePlate, vehicleType);

                    clientRepository.insertVehicle(c, newVehicle);

                    messageHelper.showMessage("Veículo inserido com sucesso.", operationType);
                } else {
                    messageHelper.showMessage("Um veículo com esta placa já está cadastrado.", operationType);
                }
            } else {
                messageHelper.showMessage(clientNotExists, operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    @Override
    public void get(int operationType) {
        String vehiclePlate = inputScannerHelper.scanInput("Qual a placa do veiculo?", operationType);
        Vehicle v = clientRepository.getVehicle(vehiclePlate);
        if (v != null) {
            messageHelper.showMessage(v.toString(), operationType);
        }else{
            messageHelper.showMessage(vehicleNotExists, operationType);
        }
    }

    @Override
    public void delete(int operationType) {
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual n° de documento do cliente?", operationType));
            Client c = clientRepository.get(clientDocument);
            if (c != null) {
                String vehiclePlate = inputScannerHelper.scanInput("Qual a placa do veiculo?", operationType);
                Vehicle v = clientRepository.getVehicle(vehiclePlate);
                if (v != null) {
                    if(parkingLotRepository.getTicket(c, v) == null) {
                        clientRepository.deleteVehicle(c, v);
                        messageHelper.showMessage("Veículo removido com sucesso.", operationType);
                    }else{
                        messageHelper.showMessage("Você não pode remover um veículo com ticket em aberto.", operationType);
                    }
                } else {
                    messageHelper.showMessage(vehicleNotExists, operationType);
                }
            } else {
                messageHelper.showMessage(clientNotExists, operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    @Override
    public void update(int operationType) {
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual n° de documento do cliente?", operationType));
            Client c = clientRepository.get(clientDocument);
            if (c != null) {
                String vehiclePlate = inputScannerHelper.scanInput("Qual a placa do veiculo?", operationType);
                Vehicle v = clientRepository.getVehicle(vehiclePlate);
                if (v != null) {
                    if(parkingLotRepository.getTicket(c, v) == null) {
                        List<String> menuItems = new ArrayList<>();
                        menuItems.add("1 - Alterar placa");
                        menuItems.add("2 - Alterar modelo");
                        menuItems.add("3 - Alterar cor");
                        menuItems.add("4 - Alterar tipo");
                        int updateType = menuItemHelper.getMenuItem(menuItems, operationType);

                        switch (updateType) {
                            case 1 -> {
                                String newLicensePlate = inputScannerHelper.scanInput("Qual a nova placa do veiculo?", operationType);
                                if (clientRepository.getVehicle(newLicensePlate) == null) {
                                    clientRepository.updateVehicle(v, newLicensePlate, Constants.VehicleUpdate.UPDATE_LICENSE_PLATE);
                                    messageHelper.showMessage("Placa alterada com sucesso.", operationType);
                                } else {
                                    messageHelper.showMessage("Um veículo com esta placa já está cadastrado.", operationType);

                                }
                            }
                            case 2 -> {
                                String newModel = inputScannerHelper.scanInput("Qual o novo modelo do veiculo?", operationType);
                                clientRepository.updateVehicle(v, newModel, Constants.VehicleUpdate.UPDATE_MODEL);
                                messageHelper.showMessage("Modelo alterado com sucesso.", operationType);
                            }
                            case 3 -> {
                                String newColor = inputScannerHelper.scanInput("Qual a nova cor do veiculo?", operationType);
                                clientRepository.updateVehicle(v, newColor, Constants.VehicleUpdate.UPDATE_COLOR);
                                messageHelper.showMessage("Cor alterada com sucesso.", operationType);
                            }
                            case 4 -> {
                                messageHelper.showMessage("Qual o novo tipo de veículo?", operationType);
                                Constants.VehicleType newVehicleType = getVehicleType(operationType);
                                clientRepository.updateVehicle(v, newVehicleType);
                                messageHelper.showMessage("Tipo do veículo alterado com sucesso.", operationType);
                            }

                            default -> messageHelper.showMessage("Você selecionou uma opção inválida.", operationType);
                        }
                    }else{
                        messageHelper.showMessage("Você não pode alterar um veículo com ticket eem aberto", operationType);
                    }
                } else {
                    messageHelper.showMessage(vehicleNotExists, operationType);
                }

            } else {
                messageHelper.showMessage(clientNotExists, operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }
}
