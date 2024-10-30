package service.model.menu_interfaces;

import service.helper.InputScannerHelper;
import service.helper.MenuItemHelper;
import service.helper.MessageHelper;

public abstract class UserInterface {

    protected final int operationType;
    protected final InputScannerHelper inputScannerHelper;
    protected final MenuItemHelper menuItemHelper;
    protected final MessageHelper messageHelper;

    public UserInterface(int operationType) {
        this.operationType = operationType;
        inputScannerHelper = new InputScannerHelper();
        menuItemHelper = new MenuItemHelper();
        messageHelper = new MessageHelper();
    }

    public abstract void execute();
    protected abstract void selectedMenuOption(int option);
}
