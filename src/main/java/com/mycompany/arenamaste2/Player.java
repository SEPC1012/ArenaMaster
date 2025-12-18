package com.mycompany.arenamaste2;
import java.util.Scanner;
public class Player extends User {
    private String nickName;
    private Game game;
    private String team;

    public Player(String name, String surname, String email, String ID, String password,
                  String rol, String nickName, Game game, String team) {
        super(name, surname, email, ID, password, rol);
        this.nickName = nickName;
        this.game = game;
        this.team = team;
    }



    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public void showMenu(ServiceContainer services) {
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("=== MENU PLAYER ===");
            System.out.println("1. Ver perfil");
            System.out.println("2. Crear equipo");
            System.out.println("3. Inscribirme a Torneo");
            System.out.println("0. Cerrar sesión");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> System.out.println("In development");
                case 2 -> System.out.println("In development");
                case 3 -> System.out.println("In development");
                case 0 -> {
                    System.out.println("Saliendo del menú...");
                    services.getAuthService().logout();

                }
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }
}
