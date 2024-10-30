package menu.menu_operations;

import service.constants.Constants;
import service.model.Client;
import service.model.ParkingSpace;
import service.model.ticket_and_fare.Ticket;
import service.model.Vehicle;
import service.model.menu_interfaces.UserOperations;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotOperations extends UserOperations {

    /*
    * Recebe por entrada o documento do cliente, se existe
    * recebe por entrada a placa do veiculo a ser estacionado, se existe
    * recebe a vaga que será estacionado, se existe
    * verifica se a vaga  está disponivel, se estiver
    * verifica se é do tipo especifico do veículo, se for
    * verifica se já existe um ticket mensal para o veículo, caso exista
    * verifica se o ticket foi pago, e se não está espirado, caso esteja ok
    * estaciona o veículo
    *
    * caso não exista um ticket mensal,
    * verifica se o ticket a ser criado é mensal ou por hora
    * caso seja por hora, o veículo é estacionado
    * caso seja mensal, o ticket é gerado, e aguarda-se o pagamento para poder estacionar
    * */
    public void parkVehicle(int operationType){
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual o número de documento do cliente?", operationType));
            Client c = clientRepository.get(clientDocument);
            if (c != null) {
                String vehicleLicensePlate = inputScannerHelper.scanInput("Qual a placa do veiculo a ser estacionado?", operationType);
                Vehicle v = clientRepository.getVehicle(vehicleLicensePlate);
                if (v != null) {
                    if (!parkingSpaceRepository.verifyVehicleIsParked(v)) {
                        int spotNumber = Integer.parseInt(inputScannerHelper.scanInput("Qual o número da vaga?", operationType));
                        ParkingSpace ps = parkingSpaceRepository.get(spotNumber);
                        if (ps != null) {
                            if (ps.getStatus() == Constants.ParkingSpaceStatus.STATUS_AVAILABLE) {
                                if (ps.getVehicleType() == v.getVehicleType()) {
                                    Ticket t = parkingLotRepository.getTicket(c, v);

                                    if (t != null && t.getType() == Constants.ParkingLotTicketType.TYPE_MONTHLY) {
                                        if (t.getFare().isPaid()) {
                                            if (!t.isExpired()) {
                                                ps.fillSpace(v);
                                                messageHelper.showMessage("Veículo estacionado com sucesso.", operationType);
                                            } else {
                                                messageHelper.showMessage("Este ticket expirou e está sendo encerrado, por favor realize o pagamento de outro ticket.", operationType);
                                                t.endTicket();
                                            }
                                        } else {
                                            messageHelper.showMessage("Para estacionar, realize o pagamento da tarifa do ticket mensal.", operationType);
                                        }
                                    } else if (t != null && t.getType() == Constants.ParkingLotTicketType.TYPE_HOURLY) {
                                        messageHelper.showMessage("Já existe um ticket relacionado a este veículo, por favor pague-o para poder criar outro.", operationType);
                                    } else {
                                        List<String> menuItems = new ArrayList<>();
                                        menuItems.add("1 - Horista");
                                        menuItems.add("2 - Mensalista");
                                        int ticketType = menuItemHelper.getMenuItem(menuItems, operationType);

                                        switch (ticketType) {
                                            case 1 -> {
                                                Constants.ParkingLotTicketType typeHourly = Constants.ParkingLotTicketType.TYPE_HOURLY;
                                                parkingLotRepository.createTicket(c, v, ps, typeHourly);
                                                ps.fillSpace(v);
                                                messageHelper.showMessage("Veículo estacionado com sucesso.", operationType);
                                            }
                                            case 2 -> {
                                                Constants.ParkingLotTicketType typeMonthly = Constants.ParkingLotTicketType.TYPE_MONTHLY;
                                                parkingLotRepository.createTicket(c, v, ps, typeMonthly);
                                                messageHelper.showMessage("Foi criado um ticket referente a este veículo, para poder estacionar quando quiser, realize o pagamento da tarifa.", operationType);
                                            }
                                        }
                                    }
                                } else {
                                    messageHelper.showMessage("Este veículo não pode estacionar nesta vaga.", operationType);
                                }
                            } else {
                                messageHelper.showMessage("Esta vaga não está disponível.", operationType);
                            }
                        } else {
                            messageHelper.showMessage("Esta vaga não existe.", operationType);
                        }
                    } else {
                        messageHelper.showMessage("Este veículo já está estacionado.", operationType);
                    }
                } else {
                    messageHelper.showMessage("Este veículo não existe.", operationType);
                }
            } else {
                messageHelper.showMessage("Este cliente não existe.", operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    /*
    * Recebe por entrada o documento do cliente, se existe
    * recebe por entrada a placa do veiculo a ser estacionado, se existe
    * verifica se o veículo está estacionado, se estiver
    * verifica se existe um ticket para este veiculo, caso haja
    * verifica o tipo de ticket
    *
    * caso seja por hora, verifica se já foi pago
    * caso já tenha sido pago, libera a vaga, e fecha o ticket
    * caso não tenha sido pago, solicita o pagamento para retirar o veiculo
    *
    * caso seja mensal, libera a vaga
    * */
    public void unparkVehicle(int operationType){
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual o número de documento do cliente?", operationType));
            Client c = clientRepository.get(clientDocument);
            if (c != null) {
                String vehicleLicensePlate = inputScannerHelper.scanInput("Qual a placa do veiculo a ser acertado?", operationType);
                Vehicle v = clientRepository.getVehicle(vehicleLicensePlate);
                if (v != null) {
                    if (parkingSpaceRepository.verifyVehicleIsParked(v)) {
                        Ticket t = parkingLotRepository.getTicket(c, v);
                        if (t != null) {
                            ParkingSpace ps = parkingSpaceRepository.get(t.getParkingSpace().getNumber());
                            switch (t.getType()) {
                                case Constants.ParkingLotTicketType.TYPE_HOURLY -> {
                                    if (t.getFare().isPaid()) {
                                        ps.emptySpace();
                                        parkingLotRepository.endTicket(t);

                                        messageHelper.showMessage("Veiculo removido com sucesso.", operationType);
                                    } else {
                                        messageHelper.showMessage("Você precisa pagar a tarifa para retirar este veículo.", operationType);
                                    }
                                }
                                case Constants.ParkingLotTicketType.TYPE_MONTHLY -> {
                                    ps.emptySpace();
                                    messageHelper.showMessage("Veiculo removido com sucesso.", operationType);
                                }
                            }

                        } else {
                            messageHelper.showMessage("Não existe um ticket para este veículo.", operationType);
                        }
                    } else {
                        messageHelper.showMessage("Este veículo não está estacionado.", operationType);
                    }
                } else {
                    messageHelper.showMessage("Este veículo não existe.", operationType);
                }
            } else {
                messageHelper.showMessage("Este cliente não existe.", operationType);
            }
        }catch (NumberFormatException e){
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
    }

    /*
    * lista os espaços disponiveis percorrendo o array de vagas, e comparando seu status com disponivel.
    * */
    public void listAllAvailable(int operationType){
        ArrayList<ParkingSpace> parkingSpaces = parkingSpaceRepository.getAll();

        StringBuilder s = new StringBuilder();
        for(ParkingSpace ps : parkingSpaces){
            if(ps.getStatus() == Constants.ParkingSpaceStatus.STATUS_AVAILABLE){
            s.append(ps.toString());
            }
        }

        messageHelper.showMessage(s.toString(), operationType);
    }


}
