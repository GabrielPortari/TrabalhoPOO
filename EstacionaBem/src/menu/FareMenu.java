package menu;
import menu.menu_operations.FareOperations;
import service.constants.menu_constants.FareMenuConstants;
import service.model.menu_interfaces.UserInterface;

import java.util.List;

public class FareMenu extends UserInterface {
    private final FareOperations fareOperations = new FareOperations();

    public FareMenu(int operationType) {
        super(operationType);
    }

    @Override
    public void execute() {
        List<String> menuOptions = FareMenuConstants.getMenuOptions();
        int option = -1;
        while(option != FareMenuConstants.FARE_MENU_EXIT.getValue() && option != FareMenuConstants.FARE_MENU_DEFAULT.getValue()){
            option = menuItemHelper.getMenuItem(menuOptions, operationType);
            selectedMenuOption(option);
        }
    }

    @Override
    protected void selectedMenuOption(int option) {
        if(FareMenuConstants.FARE_MENU_PAY.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + FareMenuConstants.FARE_MENU_PAY.getDescription(), operationType);
            fareOperations.payFare(operationType);
        }
        else if(FareMenuConstants.FARE_MENU_VERIFY.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + FareMenuConstants.FARE_MENU_VERIFY.getDescription(), operationType);
            fareOperations.verifyFare(operationType);
        }
        else if(FareMenuConstants.FARE_MENU_EXIT.getValue() == option) {
            messageHelper.showMessage("Você selecionou " + FareMenuConstants.FARE_MENU_EXIT.getDescription(), operationType);
        }else{
            messageHelper.showMessage("Você selecionou " + FareMenuConstants.FARE_MENU_DEFAULT.getDescription(), operationType);
        }
    }
}
