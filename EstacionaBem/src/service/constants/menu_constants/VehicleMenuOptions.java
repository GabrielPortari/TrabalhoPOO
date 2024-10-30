package service.constants.menu_constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum VehicleMenuOptions {
    VEHICLE_MENU_DEFAULT(0, "uma opção invalida, tente novamente."),
    VEHICLE_MENU_REGISTER(1, "cadastrar novo veículo"),
    VEHICLE_MENU_SEARCH(2, "consultar por placa"),
    VEHICLE_MENU_DELETE(3, "excluir um veículo"),
    VEHICLE_MENU_UPDATE_VEHICLE(4, "atualizar um veículos"),
    VEHICLE_MENU_EXIT(5, "voltar ao menu de clientes");


    private static final List<String> VEHICLE_MENU_OPTIONS;
    private final int value;
    private final String description;

    static {
        VEHICLE_MENU_OPTIONS = new ArrayList<>();

        for(VehicleMenuOptions option : VehicleMenuOptions.values()){
            VEHICLE_MENU_OPTIONS.add(option.value + " - " + option.description);
        }
        VEHICLE_MENU_OPTIONS.removeFirst();
    }

    VehicleMenuOptions(int value, String description){
        this.value = value;
        this.description = description;
    }

    public  int getValue(){return this.value;}
    public String getDescription(){return description;}

    public static List<String> getMenuOptions(){
        return Collections.unmodifiableList(VEHICLE_MENU_OPTIONS);
    }
}
