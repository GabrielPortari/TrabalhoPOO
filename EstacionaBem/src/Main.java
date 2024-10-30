import service.constants.Constants;
import menu.MainMenu;
import service.helper.SerializeHelper;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Object[] options = { "TERMINAL", "INTERFACE GRAFICA", "SAIR" };
        int selectedOption = JOptionPane.showOptionDialog(null, "Selecione o tipo de operação que deseja utilizar", "Estaciona Bem",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        loadData(selectedOption);

        if(selectedOption == 0){
            JOptionPane.showMessageDialog(null, "Você selecionou terminal", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            MainMenu mainMenu = new MainMenu(Constants.ExecutionType.TYPE_TERMINAL);
            mainMenu.execute();
        }else if(selectedOption == 1){
            JOptionPane.showMessageDialog(null, "Você selecionou interface gráfica", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            MainMenu mainMenu = new MainMenu(Constants.ExecutionType.TYPE_GRAPHIC);
            mainMenu.execute();
        }else{
            JOptionPane.showMessageDialog(null, "Você selecionou sair", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

        saveData(selectedOption);


    }

    private static void loadData(int selectedOption){
        if(selectedOption != 2){
            System.out.println("Carregando dados..");
            SerializeHelper serializeHelper = new SerializeHelper();
            serializeHelper.loadSystemData();
            System.out.println("Carregamento concluido.");
        }
    }

    private static void saveData(int selectedOption){
        if(selectedOption != 2){
            System.out.println("Salvando dados");
            SerializeHelper serializeHelper = new SerializeHelper();
            serializeHelper.saveSystemData();
            System.out.println("Dados salvos.");
        }
    }
}