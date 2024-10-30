package menu.menu_operations;

import service.model.Client;
import service.model.Vehicle;
import service.model.menu_interfaces.UserOperations;
import service.model.ticket_and_fare.Ticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InvoiceOperations extends UserOperations {

    /*
    * Recebe como entrada uma data no formato mm/yyyy, compara com a data dos tickets, e mostra o valor de cada ticket do mes/ano especificado
    * e ao fim mostra o total faturado neste mês
    * */
    public void listPerPeriod(int operationType) {
        ArrayList<Ticket> tickets = parkingLotRepository.getAll();
        String date = inputScannerHelper.scanInput("Qual a data a ser verificada? utilize o padrao mm/yyyy", operationType);

        String[] dateSplit = date.split("/");

        if (dateSplit.length == 2) {

            int month = Integer.parseInt(dateSplit[0]);
            int year = Integer.parseInt(dateSplit[1]);

            if (month >= 1 && month <= 12) {
                if (year >= 2020 && year <= 2024) {
                    StringBuilder s = new StringBuilder();
                    double total = 0;
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    for (Ticket t : tickets) {
                        if (t.getFare().isPaid()) {
                            int verifiedMonth = t.getFare().getPaidDate().get(Calendar.MONTH) + 1; // +1 pois os meses do Calendar vão de 0-11
                            int verifiedYear = t.getFare().getPaidDate().get(Calendar.YEAR);
                            Date dateObj = t.getFare().getPaidDate().getTime();
                            String dateFormatted = sdf.format(dateObj);
                            if (month == verifiedMonth && year == verifiedYear) {
                                s.append(dateFormatted)
                                        .append(" Valor: R$")
                                        .append(t.getFare().getValue())
                                        .append("Placa do veiculo: ")
                                        .append(t.getVehicle().getLicensePlate())
                                        .append("\n");
                                total += t.getFare().getValue();
                            }
                        }
                    }
                    s.append("\nTotal faturado: R$").append(total);
                    messageHelper.showMessage(s.toString(), operationType);
                } else {
                    messageHelper.showMessage("Ano inválido. Deve estar entre 2020 e o ano atual.", operationType);
                }
            } else {
                messageHelper.showMessage("Mês inválido. Deve estar entre 1 e 12.", operationType);
            }
        } else {
            messageHelper.showMessage("Data inválida, utilize o formato mm/yyyy", operationType);
        }

    }

    /*
    * Adiciona todos os veículos a um arraylist, e percorre todos os tickets para cada veículo, listando:
    * a placa do veiculo sendo mostrado
    * cada ticket e valor que este veículo já pagou
    * o total que este veículo já gastou no estacionamento
    * */
    public void listPerVehicle(int operationType) {
        ArrayList<Ticket> tickets = parkingLotRepository.getAll();
        ArrayList<Client> clients = clientRepository.getAllClients();
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (Client c : clients) {
            vehicles.addAll(c.getVehicles());
        }

        ArrayList<String> invoicePerVehicle = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Vehicle v : vehicles) {
            double totalPerVehicle = 0;
            StringBuilder s = new StringBuilder("Placa do veiculo: " + v.getLicensePlate());
            for (Ticket t : tickets) {
                if (t.getVehicle() != null && t.getVehicle() == v && t.getFare().isPaid()) {
                    Date dateObj = t.getFare().getPaidDate().getTime();
                    String dateFormatted = sdf.format(dateObj);
                    s.append("\nData de pagamento: ")
                            .append(dateFormatted)
                            .append("\nValor: R$")
                            .append(t.getFare().getValue())
                            .append("\n");
                    totalPerVehicle += t.getFare().getValue();
                }
            }
            s.append("Total do veículo: R$").append(totalPerVehicle);
            invoicePerVehicle.add(s.toString());
        }

        for(String s : invoicePerVehicle){
            messageHelper.showMessage(s, operationType);
        }
    }
}
