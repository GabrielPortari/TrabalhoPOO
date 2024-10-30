package service.helper;

import service.constants.Constants;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuItemHelper {

    public int getMenuItem(List<String> menuItems, int operationType){
        switch (operationType){
            case Constants.ExecutionType.TYPE_TERMINAL -> {
                return showTerminalMenu(menuItems, operationType);
            }
            case Constants.ExecutionType.TYPE_GRAPHIC -> {
                return showGraphicMenu(menuItems);
            }
            default -> {
                return 0;
            }
        }
    }

    private int showTerminalMenu(List<String> menuItems, int operationType){
        for(String s : menuItems){
            System.out.println(s);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione uma opção");
        try {
            int option = Integer.parseInt(scanner.nextLine());
            return option;
        }catch (NumberFormatException e){
            MessageHelper messageHelper = new MessageHelper();
            messageHelper.showMessage("Por favor, insira apenas numberos.", operationType);
        }
        return 0;
    }

    private int showGraphicMenu(List<String> menuItems) {
        Object selectedOption = JOptionPane.showInputDialog(null, "Selecione uma opção", "Estaciona Bem", JOptionPane.INFORMATION_MESSAGE,
                null, menuItems.toArray(), menuItems.getFirst());

        if(selectedOption != null) {
            return getIndexOfMenuItem(selectedOption, menuItems.toArray());
        }else{
            return 0;
        }
    }

    private int getIndexOfMenuItem(Object obj, Object[] objArr) {
        for(int i = 0; i< objArr.length; i++){
            if(objArr[i].equals(obj)){
                return i+1;
            }
        }
        return 0;
    }

}
