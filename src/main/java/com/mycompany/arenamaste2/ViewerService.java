package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ViewerService {

    private ServiceContainer services;

    public ViewerService(ServiceContainer services) {
        this.services = services;
    }

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("\n1. Ver torneos");
            System.out.println("2. Ver ranking de jugadores");
            System.out.println("3. Ver historial de jugador");
            System.out.println("4. Navegar Sistema de Torneos");
            System.out.println("5. Salir");
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                op = -1;
            }

            switch (op) {
                case 1 -> showTournaments();
                case 2 -> showRanking(sc);
                case 3 -> showPlayerHistory(sc);
                case 4 -> navigateTournaments(sc);
                case 5 -> System.out.println("Saliendo de vista espectador...");
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 5);
    }

    private void showRanking(Scanner sc) {
        System.out.println("\n=== SELECCIONE EL JUEGO PARA EL RANKING ===");
        List<Game> games = services.getDatabaseService().getGameDB();

        for (int i = 0; i < games.size(); i++) {
            System.out.println((i + 1) + ". " + games.get(i).getTitle());
        }
        System.out.println("0. Cancelar");
        System.out.print("Seleccione un juego: ");

        int op = -1;
        try {
            op = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            // Ignore
        }

        if (op < 1 || op > games.size())
            return;

        Game selectedGame = games.get(op - 1);

        System.out.println("\n=== RANKING DE JUGADORES - " + selectedGame.getTitle() + " ===");
        List<Player> players = new ArrayList<>();
        for (User u : services.getDatabaseService().getUsersDB().values()) {
            if (u instanceof Player p && p.getGame().equals(selectedGame)) {
                players.add(p);
            }
        }

        players.sort((p1, p2) -> Integer.compare(p2.getLastTournaments().size(), p1.getLastTournaments().size()));

        if (players.isEmpty())
            System.out.println("No hay jugadores registrados para este juego.");
        else {
            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                System.out.println(
                        (i + 1) + ". " + p.getNickName() + " (Torneos: " + p.getLastTournaments().size() + ")");
            }
        }

        System.out.println("\n=== LISTADO DE EQUIPOS DE " + selectedGame.getTitle().toUpperCase() + " ===");
        List<Team> teams = services.getDatabaseService().getTeamsDB();
        boolean foundTeam = false;

        for (Team t : teams) {
            if (t.getCaptain().getGame().equals(selectedGame)) {
                System.out.println("- " + t.getName() + " (Capitán: " + t.getCaptain().getNickName() + ")");
                foundTeam = true;
            }
        }

        if (!foundTeam)
            System.out.println("No hay equipos registrados para este juego.");

        System.out.println("\nPresione Enter para continuar...");
        sc.nextLine();
    }

    private void showPlayerHistory(Scanner sc) {
        System.out.println("\n=== HISTORIAL DE JUGADOR ===");
        System.out.print("Ingrese nickname del jugador a buscar: ");
        String busqueda = sc.nextLine();

        Player found = null;
        for (User u : services.getDatabaseService().getUsersDB().values()) {
            if (u instanceof Player p && p.getNickName().equalsIgnoreCase(busqueda)) {
                found = p;
                break;
            }
        }

        if (found == null) {
            System.out.println("Jugador no encontrado.");
            return;
        }

        System.out.println("\n--- Historial de " + found.getNickName() + " ---");
        System.out.println("Últimos Partidos:");
        List<Match> matches = found.getLastMatches();
        if (matches.isEmpty())
            System.out.println(" - Sin partidos registrados.");
        else {
            for (Match m : matches) {
                System.out.println(" - " + m.toString());
            }
        }

        System.out.println("\nÚltimos Torneos:");
        List<Tournament> tournaments = found.getLastTournaments();
        if (tournaments.isEmpty())
            System.out.println(" - Sin torneos registrados.");
        else {
            for (Tournament t : tournaments) {
                System.out.println(" - " + t.getName());
            }
        }

        System.out.println("\nPresione Enter para continuar...");
        sc.nextLine();
    }

    public void navigateTournaments(Scanner sc) {
        System.out.println("\n=== NAVEGADOR DE TORNEOS ===");
        List<Tournament> tournaments = services.getDatabaseService().getTournamentsDb();

        if (tournaments.isEmpty()) {
            System.out.println("No hay torneos registrados.");
            return;
        }

        Navigable root = new Navigable() {
            @Override
            public String getName() {
                return "Lista de Torneos";
            }

            @Override
            public List<Navigable> getChildren() {
                return new ArrayList<>(tournaments);
            }
        };

        navigate(root, sc);
    }

    private void navigate(Navigable node, Scanner sc) {
        while (true) {
            System.out.println("\n--- " + node.getName() + " ---");
            List<Navigable> children = node.getChildren();

            if (children.isEmpty()) {
                System.out.println("[Este elemento no tiene sub-elementos (Detalle Final)]");
                System.out.println("0. Regresar");

                System.out.print("Presione Enter para regresar...");
                sc.nextLine();
                return;
            }

            for (int i = 0; i < children.size(); i++) {
                System.out.println((i + 1) + ". " + children.get(i).getName());
            }
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            int op = -1;
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                // Ignore
            }

            if (op == 0) {
                return;
            }

            if (op > 0 && op <= children.size()) {
                navigate(children.get(op - 1), sc);
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    public void showGames() {
        System.out.println("\n--- Catálogo de Juegos ---");
        if (services.getDatabaseService().getGameDB().isEmpty())
            System.out.println("No hay juegos.");
        else
            services.getDatabaseService().getGameDB().forEach(j -> System.out.println("- " + j));
    }

    public void showPlayers() {
        System.out.println("\n--- Jugadores Registrados ---");

        Map<?, User> userMap = services.getDatabaseService().getUsersDB();
        List<User> allUsers = new ArrayList<>(userMap.values());

        if (allUsers.isEmpty()) {
            System.out.println("La base de datos está vacía.");
            return;
        }

        boolean hayJugadores = false;

        for (User u : allUsers) {
            if (u.getRol() == UserRole.PLAYER) {
                System.out.println("ID: " + u.getID() + " | Nombre: " + u.getName());
                hayJugadores = true;
            }
        }

        if (!hayJugadores) {
            System.out.println("No hay jugadores registrados (solo hay admins).");
        }
    }

    public void showTeams() {
        System.out.println("\n--- Equipos Registrados ---");
        System.out.println("Listado de equipos...");
    }

    public void showTournaments() {
        System.out.println(" IN development");
    }
}
