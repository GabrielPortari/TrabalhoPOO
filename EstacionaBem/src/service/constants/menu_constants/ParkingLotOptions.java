package service.constants.menu_constants;

import menu.menu_operations.ParkingLotOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum ParkingLotOptions {
    PARKING_LOT_MENU_DEFAULT(0, "uma opção invalida, tente novamente."),
    PARKING_LOT_MENU_PARK_CAR(1, "estacionar um veiculo"),
    PARKING_LOT_MENU_UNPARK_CAR(2, "desestacionar um veiculo"),
    PARKING_LOT_MENU_LIST_AVAILABLE(3, "listar todas as vagas disponiveis"),
    PARKING_LOT_MENU_MANAGE_FEE(4, "gerenciar tarifas"),
    PARKING_LOT_MENU_EXIT(5, "voltar ao menu principal");


    private static final List<String> PARKING_LOT_MENU_OPTIONS;
    private final int value;
    private final String description;

    static {
        PARKING_LOT_MENU_OPTIONS = new ArrayList<>();

        for(ParkingLotOptions option : ParkingLotOptions.values()){
            PARKING_LOT_MENU_OPTIONS.add(option.value + " - " + option.description);
        }
        PARKING_LOT_MENU_OPTIONS.removeFirst();
    }

    ParkingLotOptions(int value, String description){
        this.value = value;
        this.description = description;
    }

    public  int getValue(){return this.value;}
    public String getDescription(){return description;}

    public static List<String> getMenuOptions(){
        return Collections.unmodifiableList(PARKING_LOT_MENU_OPTIONS);
    }
}
