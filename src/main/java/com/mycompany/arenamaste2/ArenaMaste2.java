package com.mycompany.arenamaste2;

import java.util.Scanner;

public class ArenaMaste2 {

    public static void main(String[] args) {

        ServiceContainer services = new ServiceContainer();

        for (User user : services.getDatabaseService().getUsersDB().values()) {
            user.setServices(services);
        }

        mostrarMenu(services);

    }

    public static void mostrarMenu(ServiceContainer services) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n===== ARENA MASTER =====");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Ver informacion como espectador");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    services.getAuthService().login();
                }
                case 2 -> {
                    services.getAuthService().registerUser();
                }
                case 3 -> {
                    System.out.println("In development");

                }
                case 4 -> {
                    System.out.println("Saliendo del sistema...");
                }
                default -> {
                    System.out.println("Opcion invalida.");
                }
            }

        } while (opcion != 4);

        sc.close();
    }


}
