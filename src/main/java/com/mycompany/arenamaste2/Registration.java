package com.mycompany.arenamaste2;

import java.util.List;
import java.util.Scanner;

public class Registration {

  private ServiceContainer services;

  public Registration(ServiceContainer services) {
    this.services = services;
  }

  public void registerUser() {
    Scanner sc = new Scanner(System.in);

    System.out.println("registrar usuario");
    System.out.println("insertar nombre");
    String name = sc.nextLine();
    System.out.println("insertar apellido");
    String surname = sc.nextLine();
    System.out.println(" insertar email");
    String email = sc.nextLine();

    if (services.getDatabaseService().getBannedDB().contains(email)) {
      System.out.println(" baneado rctmr");
      return;
    }
    for (User u : services.getDatabaseService().getUsersDB().values()) {
      if (u.getEmail().equals(email)) {
        System.out.println(" ya sta 00");
        return;
      }
    }
    System.out.println("inserte conrtra");
    String password = sc.nextLine();
    System.out.println("inserte nickname");
    String nickname = sc.nextLine();

    List<Game> games = services.getDatabaseService().getGameDB();
    if (games.isEmpty()) {
      System.out.println("nohay juegos gg");
      return;
    }
    System.out.println("escoge un jeugo ");

    for (int i = 0; i < games.size(); i++) {
      System.out.println((i + 1) + ". " + games.get(i).getTitle());
    }
    int option;
    do {
      System.out.println("escoge mrd");
      option = sc.nextInt();
      sc.nextLine();
    } while (option < 1 || option > games.size());

    Game selectedGame = games.get(option - 1);

    String ID = "P" + String.format("%04d", services.getDatabaseService().getUsersDB().size() + 1);

    Player nuevo = new Player(name, surname, email, ID, password, UserRole.PLAYER, nickname, selectedGame, "sinTeam");
    nuevo.setServices(services);
    services.getDatabaseService().getUsersDB().put(email, nuevo); // Using email as key as per original code logic
                                                                  // (though PUT key was email but checks used
                                                                  // .equals(email) on value)
    // Note: Original code put(ID, nuevo) in DataBaseService init, but AuthService
    // put(email, nuevo).
    // Let's stick to what AuthService experienced:
    // `services.getDatabaseService().getUsersDB().put(email,nuevo);`
    // Wait, DataBaseService usersDB is Map<String, User>.
    // In DataBaseService init: usersDB.put(adminDefault.ID, adminDefault); -> ID is
    // key.
    // In AuthService registerUser:
    // services.getDatabaseService().getUsersDB().put(email,nuevo); -> Email is key.
    // This is INCONSISTENT in the original code.
    // However, AuthService login iterates over values, so key doesn't matter for
    // login.
    // Ideally it should be ID. But I will keep it as IS to avoid breaking changes
    // unless I see a reason.
    // Actually, looking at AuthService line 105:
    // `services.getDatabaseService().getUsersDB().put(email,nuevo);`
    // I will replicate that EXACTLY to preserve behavior, even if inconsistent.

    System.out.println("registro exitoso");
  }
}
