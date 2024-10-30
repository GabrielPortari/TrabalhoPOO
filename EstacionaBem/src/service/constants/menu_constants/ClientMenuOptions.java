package service.constants.menu_constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum ClientMenuOptions {
    CLIENT_MENU_DEFAULT(0, "uma opção invalida, tente novamente."),
    CLIENT_MENU_REGISTER(1, "cadastrar novo cliente"),
    CLIENT_MENU_SEARCH(2, "consultar por documento"),
    CLIENT_MENU_DELETE(3, "excluir um cliente"),
    CLIENT_MENU_MANAGE_CLIENT(4, "editar um cliente"),
    CLIENT_MENU_MANAGE_VEHICLE(5, "gerenciar veículos de um cliente"),
    CLIENT_MENU_LIST_ALL_CLIENTS(6, "listar todos os clientes"),
    CLIENT_MENU_EXIT(7, "voltar ao menu principal");


    private static final List<String> CLIENT_MENU_OPTIONS;
    private final int value;
    private final String description;

    static {
        CLIENT_MENU_OPTIONS = new ArrayList<>();

        for(ClientMenuOptions option : ClientMenuOptions.values()){
            CLIENT_MENU_OPTIONS.add(option.value + " - " + option.description);
        }
        CLIENT_MENU_OPTIONS.removeFirst();
    }

    ClientMenuOptions(int value, String description){
        this.value = value;
        this.description = description;
    }

    public  int getValue(){return this.value;}
    public String getDescription(){return description;}

    public static List<String> getMenuOptions(){
        return Collections.unmodifiableList(CLIENT_MENU_OPTIONS);
    }
}
