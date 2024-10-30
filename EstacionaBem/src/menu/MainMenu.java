package menu;

import menu.menu_operations.InvoiceOperations;
import service.constants.menu_constants.MainMenuOptions;
import service.helper.SerializeHelper;
import service.model.menu_interfaces.UserInterface;

import java.util.List;

public class MainMenu extends UserInterface {

    public MainMenu(int operationType) {
        super(operationType);
    }

    public void execute() {
        List<String> menuOptions = MainMenuOptions.getMenuOptions();
        int option = -1;
        while(option != MainMenuOptions.MAIN_MENU_EXIT.getValue() && option != MainMenuOptions.MAIN_MENU_DEFAULT.getValue()){
            option = menuItemHelper.getMenuItem(menuOptions, operationType);
            selectedMenuOption(option);
        }
    }


    protected void selectedMenuOption(int option) {
        if(MainMenuOptions.MAIN_MENU_MANAGE_CLIENT.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + MainMenuOptions.MAIN_MENU_MANAGE_CLIENT.getDescription(), operationType);
            ClientMenu clientMenu = new ClientMenu(operationType);
            clientMenu.execute();
        }
        else if(MainMenuOptions.MAIN_MENU_MANAGE_PARKING_SPACE.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + MainMenuOptions.MAIN_MENU_MANAGE_PARKING_SPACE.getDescription(), operationType);
            ParkingSpaceMenu parkingSpaceMenu = new ParkingSpaceMenu(operationType);
            parkingSpaceMenu.execute();
        }
        else if(MainMenuOptions.MAIN_MENU_MANAGE_PARKING_LOT.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + MainMenuOptions.MAIN_MENU_MANAGE_PARKING_LOT.getDescription(), operationType);
            ParkingLotMenu parkingLotMenu = new ParkingLotMenu(operationType);
            parkingLotMenu.execute();
        }
        else if(MainMenuOptions.MAIN_MENU_MANAGE_INVOICE_PER_PERIOD.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + MainMenuOptions.MAIN_MENU_MANAGE_INVOICE_PER_PERIOD.getDescription(), operationType);
            InvoiceOperations invoiceOperations = new InvoiceOperations();
            invoiceOperations.listPerPeriod(operationType);
        }
        else if(MainMenuOptions.MAIN_MENU_MANAGE_INVOICE_PER_VEHICLE.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + MainMenuOptions.MAIN_MENU_MANAGE_INVOICE_PER_VEHICLE.getDescription(), operationType);
            InvoiceOperations invoiceOperations = new InvoiceOperations();
            invoiceOperations.listPerVehicle(operationType);
        }
        else if(MainMenuOptions.MAIN_MENU_EXIT.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + MainMenuOptions.MAIN_MENU_EXIT.getDescription(), operationType);
        }else{
            messageHelper.showMessage("Você selecionou " + MainMenuOptions.MAIN_MENU_DEFAULT.getDescription(), operationType);
        }
    }
}
