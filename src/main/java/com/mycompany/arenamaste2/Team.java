package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Team implements Participant,Navigable {

    private String ID;
    private String name;
    private Player captain;
    private List<Player> players;

    private List<Match> lastMatches = new ArrayList<>();
    private List<Tournament> lastTournaments = new ArrayList<>();

    public Team(String ID, String name, Player captain, List<Player> players) {
        this.ID = ID;
        this.name = name;
        this.captain = captain;
        this.players = players;

        this.players.add(captain);
    }
//participant
    @Override
    public String getId() {
        return "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ParticipantType getType() {
        return ParticipantType.TEAM;
    }

    @Override
    public String showInfo() {
        return "";
    }

    @Override
    public void requestEnrollment(ServiceContainer services) {
        if(this.getCaptain() != null){
            services.getTournamentService().enrollParticipant(this.getCaptain());
        }else{
            System.out.println(" solo el capitan puede inscribir  el equipo");
        }
    }

    @Override
    public List<Match> getLastMatches() {
        return lastMatches;
    }

    @Override
    public void addMatch(Match match) {
        lastMatches.add(0, match);
        if (lastMatches.size() > 5) lastMatches.remove(5);
    }

    // ======= Historial de torneos =======
    @Override
    public List<Tournament> getLastTournaments() {
        return lastTournaments;
    }

    @Override
    public void addTournament(Tournament tournament) {
        lastTournaments.add(0, tournament);
        if (lastTournaments.size() > 5) lastTournaments.remove(5);
    }

    // anvigable
    @Override
    public List<Navigable> getChildren() {
        return players.stream()
                .map(p -> (Navigable) p)
                .collect(Collectors.toList());
    }


    public boolean addPlayer(Player p) {
        if (players.contains(p)) {
            System.out.println("El jugador ya está en el equipo.");
            return false;
        }
        players.add(p);
        return true;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Player getCaptain() {
        return captain;
    }

    public List<Player> getPlayers() {
        return players;
    }




}
