package com.mycompany.arenamaste2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Player extends User implements Navigable,Participant {
    private String nickName;
    private Game game;
    private String team;

    private List<Match> lastMatches = new ArrayList<>();
    private List<Tournament> lastTournaments = new ArrayList<>();

    public Player(String name, String surname, String email, String ID, String password,
                  UserRole rol, String nickName, Game game, String team) {
        super(name, surname, email, ID, password, rol);
        this.nickName = nickName;
        this.game = game;
        this.team = team;
    }
    // de user
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
                case 1 -> System.out.println(this.showInfo());
                case 2 -> services.getTournamentService().createTeam(this);
                case 3 -> requestEnrollment(services);
                case 0 -> {
                    System.out.println("Saliendo del menú...");
                    services.getAuthService().logout();

                }
                default -> System.out.println("Opción inválida");
            }
        } while (op != 0);
    }


    // de navigable
    @Override
    public List<Navigable> getChildren() {
        return Collections.emptyList();
    }

    // de participant
    @Override
    public String getId() {
        return getID();
    }

    @Override
    public ParticipantType getType() {
        return ParticipantType.PLAYER;
    }

    @Override
    public String showInfo() {
        return """
        Nick: %s
        Email: %s
        Equipo: %s
        """.formatted(
                nickName,
                email,
                team ,"Sin equipo"
        );
    }

    @Override
    public void requestEnrollment(ServiceContainer services) {
        services.getTournamentService().enrollParticipant(this);
    }
    public String getNickName() {
        return nickName;
    }

    @Override
    public List<Match> getLastMatches() { return lastMatches; }

    @Override
    public void addMatch(Match match) {
        lastMatches.add(0, match);
        if (lastMatches.size() > 5) lastMatches.remove(5);
    }
    @Override
    public List<Tournament> getLastTournaments() { return lastTournaments; }

    @Override
    public void addTournament(Tournament tournament) {
        lastTournaments.add(0, tournament);
        if (lastTournaments.size() > 5) lastTournaments.remove(5);
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
}
