package menu.menu_operations;

import service.constants.Constants;
import service.model.Client;
import service.model.ticket_and_fare.Ticket;
import service.model.Vehicle;
import service.model.menu_interfaces.UserOperations;

public class FareOperations extends UserOperations {

    /*
    * recebe por entrada documento do cliente e placa do veiculo, caso existam
    * verifica se existe um tickete me aberto para ser pago
    * caso exista, verifica se ja foi pago
    * caso nao tenha sido, realiza o pagamento
    * tickets mensais precisam ser pagos para poder estacionar o veiculo
    * tickets horistas precisam ser pagos para poder retirar o veiculo
    * caso ja tenha sido pago, avisa o usuario
    * */
    public void payFare(int operationType){
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual o número de documento do cliente?", operationType));
            Client c = clientRepository.get(clientDocument);
            if (c != null) {
                String vehicleLicensePlate = inputScannerHelper.scanInput("Qual a placa do veiculo a ser acertado?", operationType);
                Vehicle v = clientRepository.getVehicle(vehicleLicensePlate);
                if (v != null) {
                    Ticket t = parkingLotRepository.getTicket(c, v);
                    if (t != null) {
                        switch (t.getType()) {
                            case Constants.ParkingLotTicketType.TYPE_MONTHLY -> {
                                if (!t.getFare().isPaid()) {
                                    parkingLotRepository.payFare(t);
                                    messageHelper.showMessage("Tarifa no valor de " + t.getFare().getValue() + " paga com sucesso, agora você já pode estacionar seu veiculo.", operationType);
                                } else {
                                    messageHelper.showMessage("Esta tarifa já foi paga.", operationType);
                                }
                            }
                            case TYPE_HOURLY -> {
                                if (!t.getFare().isPaid()) {
                                    parkingLotRepository.payFare(t);
                                    messageHelper.showMessage("Tarifa no valor de " + t.getFare().getValue() + " paga com sucesso, agora você já pode retirar seu veiculo.", operationType);
                                } else {
                                    messageHelper.showMessage("A tarifa já foi paga, você já pode retirar o seu veículo.", operationType);
                                }
                            }
                        }
                    } else {
                        messageHelper.showMessage("Não existe um ticket relacionado a este veículo.", operationType);
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
    * recebe por entrada documento do cliente e placa do veiculo, caso existam
    * verifica se existe um ticket relacionado
    * caso exista, verifica se já foi pago, caso nao tenha sido, mostra o valor da tarifa,
    * caso tenha sido pago, e ainda nao encerrado, mostra o valor que já foi pago
    * caso ja tenha sido encerrado, mostra que nao há ticket aberto para teste veiculo
    * */
    public void verifyFare(int operationType){
        try {
            int clientDocument = Integer.parseInt(inputScannerHelper.scanInput("Qual o número de documento do cliente?", operationType));
            Client c = clientRepository.get(clientDocument);
            if (c != null) {
                String vehicleLicensePlate = inputScannerHelper.scanInput("Qual a placa do veiculo?", operationType);
                Vehicle v = clientRepository.getVehicle(vehicleLicensePlate);
                if (v != null) {
                    Ticket t = parkingLotRepository.getTicket(c, v);
                    if (t != null && !t.getFare().isPaid()) {
                        messageHelper.showMessage("O valor da tarifa é de R$" + t.getFare().getValue(), operationType);
                    } else if (t != null && t.getFare().isPaid()) {
                        messageHelper.showMessage("O ticket no valor de R$" + t.getFare().getValue() + " já foi pago.", operationType);
                    } else {
                        messageHelper.showMessage("Não existe um ticket para este veículo, ou já foi encerrado.", operationType);
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
}
