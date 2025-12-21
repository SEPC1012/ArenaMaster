package com.mycompany.arenamaste2;

import java.util.Scanner;

public class AuthService {

    private ServiceContainer services;
    private User currentUser;

    public AuthService(ServiceContainer services) {
        this.services = services;
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void login() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Inserte correo");
            String email = sc.nextLine();

            if (services.getDatabaseService().getBannedDB().contains(email)) {
                System.out.println("sta cuenta fue baneada chapa tu carboncito nomas");
                continue;
            }
            System.out.println("Inserte contraseña");
            String password = sc.nextLine();

            User user = null;
            for (User u : services.getDatabaseService().getUsersDB().values()) {
                if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                    user = u;
                    break;
                }
            }
            if (user == null) {
                System.out.println("Correo o contraseña incorrectos.");
                continue;
            }

            currentUser = user;
            break;

        }
        switch (currentUser.rol) {
            case ADMIN -> ((Admin) currentUser).showMenu(services);
            case PLAYER -> ((Player) currentUser).showMenu(services);
        }
    }

    // Registration logic moved to Registration.java

    public void logout() {
        if (currentUser != null) {
            System.out.println("sesion cerrada: " + currentUser.getName());
        }
        currentUser = null;

    }

    public void banearJugador() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Banear Jugador ---");

        System.out.print("Ingrese ID o Email del jugador: ");
        String entrada = sc.nextLine();

        System.out.println("Buscando usuario '" + entrada + "' para banear...");
        // user.setBanned(true);
    }
}
