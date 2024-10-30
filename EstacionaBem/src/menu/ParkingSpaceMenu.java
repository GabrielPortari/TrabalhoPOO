package menu;

import menu.menu_operations.ParkingSpaceOperations;
import service.constants.Constants;
import service.constants.menu_constants.ParkingSpaceMenuOptions;
import service.model.Vehicle;
import service.model.menu_interfaces.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpaceMenu extends UserInterface {
    private final ParkingSpaceOperations parkingSpaceOperations = new ParkingSpaceOperations();

    public ParkingSpaceMenu(int operationType) {
        super(operationType);
    }

    @Override
    public void execute() {
        List<String> menuOptions = ParkingSpaceMenuOptions.getMenuOptions();
        int option = -1;
        while(option != ParkingSpaceMenuOptions.PARKING_SPACE_MENU_EXIT.getValue() && option != ParkingSpaceMenuOptions.PARKING_SPACE_MENU_DEFAULT.getValue()){
            option = menuItemHelper.getMenuItem(menuOptions, operationType);
            selectedMenuOption(option);
        }
    }

    @Override
    protected void selectedMenuOption(int option) {
        if(ParkingSpaceMenuOptions.PARKING_SPACE_MENU_REGISTER.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingSpaceMenuOptions.PARKING_SPACE_MENU_REGISTER.getDescription(), operationType);
            parkingSpaceOperations.insert(operationType);
        }
        else if(ParkingSpaceMenuOptions.PARKING_SPACE_MENU_SEARCH.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingSpaceMenuOptions.PARKING_SPACE_MENU_SEARCH.getDescription(), operationType);
            parkingSpaceOperations.get(operationType);
        }
        else if(ParkingSpaceMenuOptions.PARKING_SPACE_MENU_DELETE.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingSpaceMenuOptions.PARKING_SPACE_MENU_DELETE.getDescription(), operationType);
            parkingSpaceOperations.delete(operationType);
        }
        else if(ParkingSpaceMenuOptions.PARKING_SPACE_MENU_MANAGE.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingSpaceMenuOptions.PARKING_SPACE_MENU_MANAGE.getDescription(), operationType);
            parkingSpaceOperations.update(operationType);
        }
        else if(ParkingSpaceMenuOptions.PARKING_SPACE_MENU_CHANGE_AVAILABILITY.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingSpaceMenuOptions.PARKING_SPACE_MENU_CHANGE_AVAILABILITY.getDescription(), operationType);
            parkingSpaceOperations.changeAvailability(operationType);
        }
        else if(ParkingSpaceMenuOptions.PARKING_SPACE_MENU_EXIT.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingSpaceMenuOptions.PARKING_SPACE_MENU_EXIT.getDescription(), operationType);

        }else{
            messageHelper.showMessage("Você selecionou " + ParkingSpaceMenuOptions.PARKING_SPACE_MENU_DEFAULT.getDescription(), operationType);
        }
    }


}
