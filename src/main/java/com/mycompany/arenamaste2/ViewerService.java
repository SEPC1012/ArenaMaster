package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewerService {

    private ServiceContainer services;

    public ViewerService(ServiceContainer services) {
        this.services = services;
    }

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MODO ESPECTADOR ===");
            System.out.println("1. Buscar");
            System.out.println("2. Navegar por jerarquías");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> searchForInfo(sc);
                case 2 -> navigate(sc);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private void searchForInfo(Scanner sc) {
        System.out.print("buscar: ");
        String texto = sc.nextLine().toLowerCase();

        List<Navigable> resultados = new ArrayList<>();

        for (Game g : services.getDatabaseService().getGameDB()) {
            if (g.getTitle().toLowerCase().contains(texto)) resultados.add(g);
        }

        for (Tournament t : services.getDatabaseService().getTournamentsDb()) {
            if (t.getName().toLowerCase().contains(texto)) resultados.add(t);
        }

        for (Team team : services.getDatabaseService().getTeamsDB()) {
            if (team.getName().toLowerCase().contains(texto)) resultados.add(team);
        }

        for (User u : services.getDatabaseService().getUsersDB().values()) {
            if (u instanceof Player p) {
                if (p.getNickName().toLowerCase().contains(texto)) resultados.add(p);
            }
        }

        if (resultados.isEmpty()) {
            System.out.println("No se encontró nada.");
            return;
        }

        System.out.println("\nResultados:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println((i + 1) + ". " + resultados.get(i).getName());
        }

        System.out.print("Selecciona un número o 0 para salir: ");
        int select = sc.nextInt();
        sc.nextLine();

        if (select > 0 && select <= resultados.size()) {
            navegarElemento(resultados.get(select - 1), sc);
        }
    }

    private void navigate(Scanner sc) {
        List<Game> juegos = services.getDatabaseService().getGameDB();
        List<Navigable> listaJuegos = new ArrayList<>(juegos);

        navigateList(listaJuegos, sc);
    }

    private void navigateList(List<Navigable> lista, Scanner sc) {
        if (lista.isEmpty()) {
            System.out.println("No hay elementos para mostrar.");
            return;
        }

        while (true) {
            System.out.println("\n=== SELECCIONA UNA OPCIÓN ===");
            int limit = Math.min(10, lista.size());
            for (int i = 0; i < limit; i++) {
                System.out.println((i + 1) + ". " + lista.get(i).getName());
            }
            System.out.println("0. Salir");

            System.out.print("escoge un numero: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 0) break;
            if (option < 1 || option > limit) {
                System.out.println("esocge bien.");
                continue;
            }

            Navigable selected = lista.get(option - 1);
            showDetails(selected);

            List<Navigable> children;
            children = selected.getChildren();
            if (!children.isEmpty()) {
                navigateList(children, sc);
            } else {
                System.out.println("No hay más elementos ");
                sc.nextLine();
            }
        }
    }

    private void showDetails(Navigable obj) {
        System.out.println("detalles");

        if (obj instanceof Game g) {
            System.out.println("juego: " + g.getTitle());
        } else if (obj instanceof Tournament t) {
            System.out.println("torneo: " + t.getName());
            System.out.println("participantes: " + t.getParticipants().size());
        } else if (obj instanceof Team team) {
            System.out.println("equipo: " + team.getName());
            System.out.println("jugadores: " + team.getPlayers().size());
        } else if (obj instanceof Player p) {
            System.out.println("jugador: " + p.getNickName());
            System.out.println("nombre real: " + p.getName());
            System.out.println("email: " + p.getEmail());
        }
    }

    private void navegarElemento(Navigable obj, Scanner sc) {
        showDetails(obj);
        List<Navigable> children = obj.getChildren();
        if (!children.isEmpty()) navigateList(children, sc);
    }
}
