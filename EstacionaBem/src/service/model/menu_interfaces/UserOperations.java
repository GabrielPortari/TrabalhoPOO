package service.model.menu_interfaces;

import service.constants.Constants;
import service.helper.InputScannerHelper;
import service.helper.MenuItemHelper;
import service.helper.MessageHelper;
import service.repository.ClientRepository;
import service.repository.ParkingLotRepository;
import service.repository.ParkingSpaceRepository;

import java.util.ArrayList;
import java.util.List;

public class UserOperations {
    protected final ClientRepository clientRepository = new ClientRepository();
    protected final ParkingSpaceRepository parkingSpaceRepository = new ParkingSpaceRepository();
    protected final ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

    protected final InputScannerHelper inputScannerHelper = new InputScannerHelper();
    protected final MenuItemHelper menuItemHelper = new MenuItemHelper();
    protected final MessageHelper messageHelper = new MessageHelper();

    protected Constants.VehicleType getVehicleType(int operationType){
        List<String> menuItems = new ArrayList<>();
        menuItems.add("1 - Carro");
        menuItems.add("2 - Moto");
        menuItems.add("3 - Onibus");
        int vType = menuItemHelper.getMenuItem(menuItems, operationType);
        return switch (vType){
            case 1 -> Constants.VehicleType.TYPE_CAR;
            case 2 -> Constants.VehicleType.TYPE_MOTORCYCLE;
            case 3 -> Constants.VehicleType.TYPE_BUS;
            default -> Constants.VehicleType.TYPE_CAR;
        };
    }
}
