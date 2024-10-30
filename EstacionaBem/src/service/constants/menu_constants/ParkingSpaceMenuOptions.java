package service.constants.menu_constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum ParkingSpaceMenuOptions {
    PARKING_SPACE_MENU_DEFAULT(0, "uma opção invalida, tente novamente."),
    PARKING_SPACE_MENU_REGISTER(1, "cadastrar nova vaga"),
    PARKING_SPACE_MENU_SEARCH(2, "consultar por numero"),
    PARKING_SPACE_MENU_DELETE(3, "excluir uma vaga"),
    PARKING_SPACE_MENU_MANAGE(4, "editar uma vaga"),
    PARKING_SPACE_MENU_CHANGE_AVAILABILITY(5, "gerenciar disponibilidade de vaga"),
    PARKING_SPACE_MENU_EXIT(6, "voltar ao menu principal");

    private static final List<String> PARKING_SPACE_MENU_OPTIONS;
    private final int value;
    private final String description;

    static {
        PARKING_SPACE_MENU_OPTIONS = new ArrayList<>();

        for(ParkingSpaceMenuOptions option : ParkingSpaceMenuOptions.values()){
            PARKING_SPACE_MENU_OPTIONS.add(option.value + " - " + option.description);
        }
        PARKING_SPACE_MENU_OPTIONS.removeFirst();
    }

    ParkingSpaceMenuOptions(int value, String description){
        this.value = value;
        this.description = description;
    }

    public  int getValue(){return this.value;}
    public String getDescription(){return description;}

    public static List<String> getMenuOptions(){
        return Collections.unmodifiableList(PARKING_SPACE_MENU_OPTIONS);
    }
}
