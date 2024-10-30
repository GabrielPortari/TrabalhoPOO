package menu;

import menu.menu_operations.VehicleOperations;
import service.constants.menu_constants.VehicleMenuOptions;
import service.model.menu_interfaces.UserInterface;

import java.util.List;

public class VehicleMenu extends UserInterface {
    private final VehicleOperations vehicleOperations = new VehicleOperations();

    public VehicleMenu(int operationType) {
        super(operationType);
    }

    @Override
    public void execute() {
        List<String> menuOptions = VehicleMenuOptions.getMenuOptions();
        int option = -1;
        while(option != VehicleMenuOptions.VEHICLE_MENU_EXIT.getValue() && option != VehicleMenuOptions.VEHICLE_MENU_DEFAULT.getValue()){
            option = menuItemHelper.getMenuItem(menuOptions, operationType);
            selectedMenuOption(option);
        }
    }

    @Override
    protected void selectedMenuOption(int option) {
        if(VehicleMenuOptions.VEHICLE_MENU_REGISTER.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + VehicleMenuOptions.VEHICLE_MENU_REGISTER.getDescription(), operationType);
            vehicleOperations.insert(operationType);
        }
        else if(VehicleMenuOptions.VEHICLE_MENU_SEARCH.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + VehicleMenuOptions.VEHICLE_MENU_SEARCH.getDescription(), operationType);
            vehicleOperations.get(operationType);
        }
        else if(VehicleMenuOptions.VEHICLE_MENU_DELETE.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + VehicleMenuOptions.VEHICLE_MENU_DELETE.getDescription(), operationType);
            vehicleOperations.delete(operationType);
        }
        else if(VehicleMenuOptions.VEHICLE_MENU_UPDATE_VEHICLE.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + VehicleMenuOptions.VEHICLE_MENU_UPDATE_VEHICLE.getDescription(), operationType);
            vehicleOperations.update(operationType);
        }
        else if(VehicleMenuOptions.VEHICLE_MENU_EXIT.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + VehicleMenuOptions.VEHICLE_MENU_EXIT.getDescription(), operationType);

        }else{
            messageHelper.showMessage("Você selecionou " + VehicleMenuOptions.VEHICLE_MENU_DEFAULT.getDescription(), operationType);
        }
    }

}
