package service.helper;

import service.constants.Constants;

import javax.swing.*;
import java.util.Scanner;

public class InputScannerHelper {
    private final Scanner scanner = new Scanner(System.in);


    public String scanInput(String message, int operationType){
        switch (operationType){
            case Constants.ExecutionType.TYPE_TERMINAL -> {
                return terminalScanner(message);
            }
            case Constants.ExecutionType.TYPE_GRAPHIC -> {
                return graphicScanner(message);
            }
        }
        return "Ocorreu um erro inesperado!s";
    }
    private String terminalScanner(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

    private String graphicScanner(String message){
        return JOptionPane.showInputDialog(message);
    }
}
