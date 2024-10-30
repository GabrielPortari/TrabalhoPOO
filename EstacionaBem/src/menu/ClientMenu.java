package menu;

import service.constants.menu_constants.ClientMenuOptions;
import service.model.menu_interfaces.UserInterface;
import menu.menu_operations.ClientOperations;

import java.util.List;

public class ClientMenu extends UserInterface {
    private final ClientOperations clientOperations = new ClientOperations();

    public ClientMenu(int operationType) {
        super(operationType);
    }

    @Override
    public void execute() {
        List<String> menuOptions = ClientMenuOptions.getMenuOptions();
        int option = -1;
        while(option != ClientMenuOptions.CLIENT_MENU_EXIT.getValue() && option != ClientMenuOptions.CLIENT_MENU_DEFAULT.getValue()){
            option = menuItemHelper.getMenuItem(menuOptions, operationType);
            selectedMenuOption(option);
        }
    }

    @Override
    protected void selectedMenuOption(int option) {
        if(ClientMenuOptions.CLIENT_MENU_REGISTER.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ClientMenuOptions.CLIENT_MENU_REGISTER.getDescription(), operationType);
            clientOperations.insert(operationType);
        }
        else if(ClientMenuOptions.CLIENT_MENU_SEARCH.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ClientMenuOptions.CLIENT_MENU_SEARCH.getDescription(), operationType);
            clientOperations.get(operationType);
        }
        else if(ClientMenuOptions.CLIENT_MENU_DELETE.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ClientMenuOptions.CLIENT_MENU_DELETE.getDescription(), operationType);
            clientOperations.delete(operationType);
        }
        else if(ClientMenuOptions.CLIENT_MENU_MANAGE_CLIENT.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ClientMenuOptions.CLIENT_MENU_MANAGE_CLIENT.getDescription(), operationType);
            clientOperations.update(operationType);
        }
        else if(ClientMenuOptions.CLIENT_MENU_MANAGE_VEHICLE.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ClientMenuOptions.CLIENT_MENU_MANAGE_VEHICLE.getDescription(), operationType);
            VehicleMenu vehicleMenu = new VehicleMenu(operationType);
            vehicleMenu.execute();
        }
        else if(ClientMenuOptions.CLIENT_MENU_LIST_ALL_CLIENTS.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ClientMenuOptions.CLIENT_MENU_LIST_ALL_CLIENTS.getDescription(), operationType);
            clientOperations.getAllClients(operationType);
        }
        else if(ClientMenuOptions.CLIENT_MENU_EXIT.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + ClientMenuOptions.CLIENT_MENU_EXIT.getDescription(), operationType);
        }else{
            messageHelper.showMessage("Você selecionou " + ClientMenuOptions.CLIENT_MENU_DEFAULT.getDescription(), operationType);
        }
    }
}
