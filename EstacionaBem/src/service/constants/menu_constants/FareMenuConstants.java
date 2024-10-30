package service.constants.menu_constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum FareMenuConstants {
    FARE_MENU_DEFAULT(0, "uma opção invalida, tente novamente."),
    FARE_MENU_PAY(1, "pagar uma tarifa"),
    FARE_MENU_VERIFY(2, "verificar valor de uma tarifa"),
    FARE_MENU_EXIT(3, "voltar ao menu de estacionamento");


    private static final List<String> FARE_MENU_OPTIONS;
    private final int value;
    private final String description;

    static {
        FARE_MENU_OPTIONS = new ArrayList<>();

        for(FareMenuConstants option : FareMenuConstants.values()){
            FARE_MENU_OPTIONS.add(option.value + " - " + option.description);
        }
        FARE_MENU_OPTIONS.removeFirst();
    }

    FareMenuConstants(int value, String description){
        this.value = value;
        this.description = description;
    }

    public  int getValue(){return this.value;}
    public String getDescription(){return description;}

    public static List<String> getMenuOptions(){
        return Collections.unmodifiableList(FARE_MENU_OPTIONS);
    }
}
