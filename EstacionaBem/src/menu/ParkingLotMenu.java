package menu;

import menu.menu_operations.ParkingLotOperations;
import service.constants.menu_constants.ClientMenuOptions;
import service.constants.menu_constants.ParkingLotOptions;
import service.model.menu_interfaces.UserInterface;

import java.util.List;

public class ParkingLotMenu extends UserInterface {
    private final ParkingLotOperations parkingLotOperations = new ParkingLotOperations();

    public ParkingLotMenu(int operationType) {
        super(operationType);
    }

    @Override
    public void execute() {
        List<String> menuOptions = ParkingLotOptions.getMenuOptions();
        int option = -1;
        while(option != ParkingLotOptions.PARKING_LOT_MENU_EXIT.getValue() && option != ParkingLotOptions.PARKING_LOT_MENU_DEFAULT.getValue()){
            option = menuItemHelper.getMenuItem(menuOptions, operationType);
            selectedMenuOption(option);
        }
    }

    @Override
    protected void selectedMenuOption(int option) {
        if(ParkingLotOptions.PARKING_LOT_MENU_PARK_CAR.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingLotOptions.PARKING_LOT_MENU_PARK_CAR.getDescription(), operationType);
            parkingLotOperations.parkVehicle(operationType);
        }
        else if(ParkingLotOptions.PARKING_LOT_MENU_UNPARK_CAR.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingLotOptions.PARKING_LOT_MENU_UNPARK_CAR.getDescription(), operationType);
            parkingLotOperations.unparkVehicle(operationType);
        }
        else if(ParkingLotOptions.PARKING_LOT_MENU_LIST_AVAILABLE.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingLotOptions.PARKING_LOT_MENU_LIST_AVAILABLE.getDescription(), operationType);
            parkingLotOperations.listAllAvailable(operationType);
        }
        else if(ParkingLotOptions.PARKING_LOT_MENU_MANAGE_FEE.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingLotOptions.PARKING_LOT_MENU_MANAGE_FEE.getDescription(), operationType);
            FareMenu feeMenu = new FareMenu(operationType);
            feeMenu.execute();
        }
        else if(ParkingLotOptions.PARKING_LOT_MENU_EXIT.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ParkingLotOptions.PARKING_LOT_MENU_EXIT.getDescription(), operationType);

        }else{
            messageHelper.showMessage("Você selecionou " + ClientMenuOptions.CLIENT_MENU_DEFAULT.getDescription(), operationType);
        }
    }
}
