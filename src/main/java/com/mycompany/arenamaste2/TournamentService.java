package com.mycompany.arenamaste2;

import java.util.*;

public class TournamentService {

    private ServiceContainer services;

    public TournamentService(ServiceContainer services) {
        this.services = services;
    }

    public ServiceContainer getServices() {
        return services;
    }


    public void enrollParticipant(Player player){
        System.out.println("Inscribir Participante");

        List<Tournament> tts = services.getDatabaseService().getTournamentsDb();
        if (tts.isEmpty()) {
            System.out.println("No hay torneos disponibles.");
            return;
        }

        System.out.println("Torneos disponibles:");
        List<Tournament> openTournaments = new ArrayList<>();
        for (int i = 0; i < tts.size(); i++) {
            Tournament tt = tts.get(i);
            if (tt.isClosed()) {
                System.out.println((i + 1) + ". " + tt.getName() + " (CERRADO)");
                continue;
            }
            openTournaments.add(tt);
            System.out.println((openTournaments.size()) + ". " + tt.getName() +
                    " | Tipo: " + tt.getAllowedParticipantType() +
                    " | Participantes: " + tt.getParticipants().size() +
                    "/" + tt.getMaxParticipant());
        }

        if (openTournaments.isEmpty()) {
            System.out.println("No hay torneos abiertos para inscripción.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Seleccione torneo: ");
        int op = sc.nextInt();
        sc.nextLine();

        if (op < 1 || op > openTournaments.size()) {
            System.out.println("Opción inválida.");
            return;
        }

        Tournament tt = openTournaments.get(op - 1);

        if (tt.getAllowedParticipantType() == ParticipantType.TEAM) {
            Team team = services.getDatabaseService().getTeamsDB().stream()
                    .filter(tm -> tm.getCaptain() == player)
                    .findFirst()
                    .orElse(null);

            if (team == null) {
                System.out.println("No tienes un equipo asignado para inscribir.");
                return;
            }

            if (tt.getParticipants().contains(team)) {
                System.out.println("Este equipo ya está inscrito en el torneo.");
                return;
            }

            tt.getParticipants().add(team);
            System.out.println("Equipo " + team.getName() + " inscrito en " + tt.getName());

        } else {
            if (tt.getParticipants().contains(player)) {
                System.out.println("Ya estás inscrito en este torneo.");
                return;
            }

            tt.getParticipants().add(player);
            System.out.println("Jugador " + player.getNickName() + " inscrito en " + tt.getName());
        }

        if (tt.isFull()) {
            System.out.println("Torneo lleno. Se simulará automáticamente.");
            simulateTournament(tt);
        }
    }

    public void createTeam(Player captain){
        System.out.println("Crear team");
        if(!captain.getTeam().equals("sinTeam")){
            System.out.println("ya tienes equipo ");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Crear nombre");
        String name = sc.nextLine();
        Team team = new Team(
                "T" + (services.getDatabaseService().getTeamsDB().size() + 1),
                name,
                captain,
                new ArrayList<>()

        );
        team.addPlayer(captain);
        captain.setTeam(name);

        while(team.getPlayers().size()<5){
            List<Player> dispo = new ArrayList<>();
            for(User u:services.getDatabaseService().getUsersDB().values()){
                if (u instanceof Player p){
                    if (p.getTeam().equals("sinTeam")&&p != captain){
                        dispo.add(p);
                    }
                }
            }
            if (dispo.isEmpty()){
                System.out.println( "no hay jjugadores disponibles");
                break;
            }
            System.out.println("jugadores disponibles");
            for (int i =0; i < dispo.size();i++){
                System.out.println((i+1)+". "+ dispo.get(i).getNickName());
            }

            System.out.println("0. terminar");
            System.out.println("seleciones jugador: ");
            int op= sc.nextInt();
            sc.nextLine();

            if(op == 0) break;

            if (op<1 || op> dispo.size()){
                System.out.println(" opcion invalida");
                continue;
            }

            Player selected = dispo.get(op-1);
            team.addPlayer(selected);
            selected.setTeam(name);

            System.out.println(selected.getNickName()+"agregado al equuipo");
        }
        services.getDatabaseService().getTeamsDB().add(team);
        System.out.println("equipo creado y registrado");

    }

    public void verifyTournament(){
        Scanner sc = new Scanner(System.in);
        List<Tournament> ts = services.getDatabaseService().getTournamentsDb();

        if (ts.isEmpty()) {
            System.out.println("⚠No hay torneos registrados para simular.");
            return;
        }

        System.out.println("\n--- Seleccionar Torneo ---");
        for (Tournament t : ts) {
            System.out.println("ID: " + t.getID() + " | Nombre: " + t.getName() +
                    " | Estado: " + (t.isClosed() ? "Cerrado" : "Abierto"));
        }

        System.out.print("Ingrese ID del torneo a simular: ");
        String idBuscado = sc.nextLine(); // O sc.nextInt() si tu ID es int

        Tournament tt = null;
        for (Tournament t : ts) {
            if (t.getID().equals(idBuscado)) {
                tt = t;
                break;
            }
        }

        if (tt == null) {
            System.out.println("Torneo no encontrado.");
            return;
        }
        simulateTournament(tt);
    }
    public void simulateTournament(Tournament tt){
        Scanner sc = new Scanner(System.in);
        if (tt.isClosed()) {
            System.out.println("Torneo " + tt.getName() + " ya ha sido simulado.");
            return;
        }

        if (tt.getParticipants().isEmpty()) {
            System.out.println("No hay participantes en el torneo.");
            return;
        }

        if(!tt.isFull()){
            System.out.println("el torneo todavia no se llena; simular de todos modos?(1. Si; 2. No)");
            int op = sc.nextInt();
            if (op != 1){
                return;
            }
        }

        Collections.shuffle(tt.getParticipants());

        List<Participant> currentRound = new ArrayList<>(tt.getParticipants());
        List<Participant> nextRound;
        int numRound = 1;

        while (currentRound.size() > 1) {
            String nombreRonda;
            if (currentRound.size() >= 8) nombreRonda = "=== CUARTOS DE FINAL ===";
            else if (currentRound.size() >= 4) nombreRonda = "=== SEMIFINALES ===";
            else nombreRonda = "=== FINAL ===";

            System.out.println("\n" + nombreRonda);

            List<Match> matches = new ArrayList<>();
            for (int i = 0; i < currentRound.size(); i += 2) {
                if (i + 1 >= currentRound.size()) {
                    System.out.println(currentRound.get(i).getName() + " pasa automaticamente a la siguiente ronda.");
                    matches.add(new Match(currentRound.get(i), currentRound.get(i)));
                } else {
                    matches.add(new Match(currentRound.get(i), currentRound.get(i + 1)));
                }
            }

            Round ronda = new Round(matches);

            tt.getRounds().add(ronda);
            tt.getMatches().addAll(matches);

            nextRound = ronda.simularRonda();
            currentRound = nextRound;
            numRound++;
        }

        Participant campeon = currentRound.get(0);
        System.out.println("\n=== GANADOR DEL TORNEO " + tt.getName() + " ===");
        System.out.println("1°: " + campeon.getName());

        tt.setClosed(true);
    }

    public void createTournament() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Crear Nuevo Torneo ---");

        String newID;
        boolean idExiste;

        do {
            newID = UUID.randomUUID().toString().substring(0, 8);
            idExiste = false;

            for (Tournament t : services.getDatabaseService().getTournamentsDb()) {
                if (t.getID().equals(newID)) {
                    idExiste = true;
                    break;
                }
            }
        } while (idExiste);

        System.out.println("ID Generado: " + newID);

        System.out.print("Nombre del torneo: ");
        String name = sc.nextLine();

        List<Game> gamesList = services.getDatabaseService().getGameDB();

        if (gamesList.isEmpty()) {
            System.out.println("Error: No hay juegos registrados. Crea un juego primero (Opción 4).");
            return;
        }

        System.out.println("Seleccione el Juego para el torneo:");
        for (int i = 0; i < gamesList.size(); i++) {
            System.out.println((i + 1) + ". " + gamesList.get(i).getName());
        }

        System.out.print("Opción: ");
        int gameIndex = sc.nextInt() - 1;
        sc.nextLine();

        if (gameIndex < 0 || gameIndex >= gamesList.size()) {
            System.out.println(" Opción de juego inválida.");
            return;
        }
        Game selectedGame = gamesList.get(gameIndex);

        System.out.println("Tipo de participación:");
        System.out.println("1. Individual (Player)");
        System.out.println("2. Equipo (Team)");
        System.out.print("Opción: ");
        int typeOp = sc.nextInt();

        ParticipantType type = (typeOp == 2) ? ParticipantType.TEAM : ParticipantType.PLAYER;

        System.out.print("Cantidad máxima de participantes: ");
        int maxParticipants = sc.nextInt();
        sc.nextLine();

        Tournament newTournament = new Tournament(newID, name, selectedGame, type, false, maxParticipants);

        services.getDatabaseService().getTournamentsDb().add(newTournament);

        System.out.println("Torneo '" + name + "' (ID: " + newID + ") creado exitosamente.");
    }

}