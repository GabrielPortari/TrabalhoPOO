package service.helper;

import service.constants.Constants;

import javax.swing.*;

public class MessageHelper {

    public void showMessage(String message, int operationType){
        switch (operationType) {
            case Constants.ExecutionType.TYPE_TERMINAL -> {
                    System.out.println(message);
            }
            case Constants.ExecutionType.TYPE_GRAPHIC -> {
                JOptionPane.showMessageDialog(null, message, "Estaciona Bem", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
