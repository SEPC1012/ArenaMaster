package com.mycompany.arenamaste2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {

    public Admin(String name, String surname, String email, String ID, String password, UserRole rol) {
        super(name, surname, email, ID, password, rol);
    }

    public void simularTorneo() {
        System.out.println("In development");
    }

    @Override
    public void showMenu(ServiceContainer services) {
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1. Crear torneo");
            System.out.println("2. Ver torneos");
            System.out.println("3. simular torneo");
            System.out.println("4. Agregar nuevo juego");
            System.out.println("5. Ver equipos");
            System.out.println("6. Ver juegos");
            System.out.println("7. Ver jugadores registrados");
            System.out.println("8. Banear jugadores");
            System.out.println("9. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                case 1 -> {
                    System.out.println("\n--- Crear Torneo ---");
                    services.getTournamentService().createTournament();
                }

                case 2 -> {
                    System.out.println("\n--- Torneos ---");
                    services.getViewerService().showTournaments();
                }

                case 3 -> {
                    services.getTournamentService().verifyTournament();

                    System.out.println("In development");

                }

                case 4 -> {
                    services.getDatabaseService().addGame();
                    System.out.println("In development");

                }

                case 5 -> {
                    services.getViewerService().showTeams();
                    System.out.println("\n--- Equipos ---");


                }

                case 6 -> {
                    services.getViewerService().showGames();
                    System.out.println("\n--- Juegos ---");


                }

                case 7 -> {
                    services.getViewerService().showPlayers();
                    System.out.println("In development");

                }

                case 8 -> {
                    services.getAuthService().banearJugador();
                    System.out.println("In development");
                }
                case 9->{
                    System.out.println("Cerrando sesión...");
                    services.getAuthService().logout();


                }

                default -> {
                    System.out.println("Opción inválida.");
                }
            }

        } while (option != 9);
    }
}
