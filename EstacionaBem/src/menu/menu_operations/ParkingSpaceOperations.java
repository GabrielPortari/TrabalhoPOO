package menu.menu_operations;

import service.constants.Constants;
import service.model.ParkingSpace;
import service.model.menu_interfaces.UserOperations;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpaceOperations extends UserOperations {
    private final String parkingSpaceNotExists = "Esta vaga não existe.";

    public void insert(int operationType){
        try {
            int psNumber = Integer.parseInt(inputScannerHelper.scanInput("Qual o número da vaga?", operationType));
            if (parkingSpaceRepository.get(psNumber) == null) {
                String psStreet = inputScannerHelper.scanInput("Qual a rua da vaga?", operationType);
                messageHelper.showMessage("Qual o tipo de veiculo da vaga?", operationType);
                Constants.VehicleType psVehicleType = getVehicleType(operationType);
                ParkingSpace ps = new ParkingSpace(psNumber, psStreet, psVehicleType);
                parkingSpaceRepository.insert(ps);
                messageHelper.showMessage("Vaga cadastrada com sucesso.", operationType);
            } else {
                messageHelper.showMessage("Uma vaga com este número já existe.", operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    public void get(int operationType){
        int psNumber = Integer.parseInt(inputScannerHelper.scanInput("Qual o número da vaga?", operationType));
        ParkingSpace ps = parkingSpaceRepository.get(psNumber);

        if(ps != null){
            messageHelper.showMessage(ps.toString(), operationType);
        }else{
            messageHelper.showMessage(parkingSpaceNotExists, operationType);
        }
    }

    public void delete(int operationType){
        try {
            int psNumber = Integer.parseInt(inputScannerHelper.scanInput("Qual o número da vaga?", operationType));
            ParkingSpace ps = parkingSpaceRepository.get(psNumber);

            if (ps != null) {
                if(ps.getStatus() != Constants.ParkingSpaceStatus.STATUS_BUSY) {
                    parkingSpaceRepository.delete(ps);
                    messageHelper.showMessage("Vaga excluída com sucesso.", operationType);
                }else{
                    messageHelper.showMessage("Uma vaga só pode ser excluida se não estiver ocupada.", operationType);
                }
            } else {
                messageHelper.showMessage(parkingSpaceNotExists, operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    public void update(int operationType){
        try {
            int psNumber = Integer.parseInt(inputScannerHelper.scanInput("Qual o número da vaga?", operationType));
            ParkingSpace ps = parkingSpaceRepository.get(psNumber);

            if (ps != null) {
                if(ps.getStatus() != Constants.ParkingSpaceStatus.STATUS_BUSY) {
                    List<String> menuItems = new ArrayList<>();
                    menuItems.add("1 - Alterar número");
                    menuItems.add("2 - Alterar rua");
                    menuItems.add("2 - Alterar tipo de veículo");
                    int updateType = menuItemHelper.getMenuItem(menuItems, operationType);

                    switch (updateType) {
                        case 1 -> {
                            int newNumber = Integer.parseInt(inputScannerHelper.scanInput("Qual o novo número da vaga?", operationType));
                            if (parkingSpaceRepository.get(newNumber) == null) {
                                parkingSpaceRepository.update(ps, newNumber);
                                messageHelper.showMessage("Número da vaga alterado com sucesso.", operationType);
                            } else {
                                messageHelper.showMessage("Uma vaga com este número já existe.", operationType);
                            }
                        }
                        case 2 -> {
                            String newStreet = inputScannerHelper.scanInput("Qual a nova rua da vaga?", operationType);
                            parkingSpaceRepository.update(ps, newStreet);
                            messageHelper.showMessage("Rua da vaga alterada com sucesso", operationType);
                        }
                        case 3 -> {
                            Constants.VehicleType newVehicleType = getVehicleType(operationType);
                            parkingSpaceRepository.update(ps, newVehicleType);
                            messageHelper.showMessage("Tipo de veículo da vaga alterado com sucesso", operationType);
                        }
                        default -> messageHelper.showMessage("Você selecionou uma opção inválida.", operationType);
                    }
                }else{
                    messageHelper.showMessage("Uma vaga só pode ser alterada se não estiver ocupada.", operationType);
                }
            } else {
                messageHelper.showMessage(parkingSpaceNotExists, operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    public void changeAvailability(int operationType){
        try {
            int psNumber = Integer.parseInt(inputScannerHelper.scanInput("Qual o número da vaga?", operationType));
            ParkingSpace ps = parkingSpaceRepository.get(psNumber);

            if (ps != null) {
                if(ps.getStatus() != Constants.ParkingSpaceStatus.STATUS_BUSY) {
                    List<String> menuItems = new ArrayList<>();
                    menuItems.add("1 - Alterar para disponível");
                    menuItems.add("2 - Alterar para indisponível");
                    int updateType = menuItemHelper.getMenuItem(menuItems, operationType);

                    Constants.ParkingSpaceStatus status = switch (updateType) {
                        case 1 -> Constants.ParkingSpaceStatus.STATUS_AVAILABLE;
                        case 2 -> Constants.ParkingSpaceStatus.STATUS_UNAVAILABLE;
                        default -> ps.getStatus();
                    };
                    if (ps.getStatus() != status) {
                        parkingSpaceRepository.changeAvailability(ps, status);
                    } else {
                        messageHelper.showMessage("Esta vaga já esta com este status.", operationType);
                    }
                }else{
                    messageHelper.showMessage("Uma vaga só pode ser excluida se não estiver ocupada.", operationType);
                }
            } else {
                messageHelper.showMessage(parkingSpaceNotExists, operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }
}
