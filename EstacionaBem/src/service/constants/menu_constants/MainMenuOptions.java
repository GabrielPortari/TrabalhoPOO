package service.constants.menu_constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum MainMenuOptions {
    MAIN_MENU_DEFAULT(0, "uma opção invalida, tente novamente."),
    MAIN_MENU_MANAGE_CLIENT(1, "gerenciar cliente"),
    MAIN_MENU_MANAGE_PARKING_SPACE(2, "gerenciar vagas"),
    MAIN_MENU_MANAGE_PARKING_LOT(3, "gerenciar estacionamento"),
    MAIN_MENU_MANAGE_INVOICE_PER_PERIOD(4, "consultar total faturado em um periodo"),
    MAIN_MENU_MANAGE_INVOICE_PER_VEHICLE(5, "consultar total faturado por veiculo"),
    MAIN_MENU_EXIT(6, "sair do programa");


    private static final List<String> MAIN_MENU_OPTIONS;
    private final int value;
    private final String description;

    static {
        MAIN_MENU_OPTIONS = new ArrayList<>();

        for(MainMenuOptions option : MainMenuOptions.values()){
            MAIN_MENU_OPTIONS.add(option.value + " - " + option.description);
        }
        MAIN_MENU_OPTIONS.removeFirst();
    }

    MainMenuOptions(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue(){return this.value;}
    public String getDescription(){return description;}

    public static List<String> getMenuOptions(){
        return Collections.unmodifiableList(MAIN_MENU_OPTIONS);
    }

}
