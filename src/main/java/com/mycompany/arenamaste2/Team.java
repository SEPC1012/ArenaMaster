package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.List;

public class Team implements Participant {

    private String ID;
    private String name;
    private Player captain;
    private List<Player> players;

    public Team(String ID, String name, Player captain, List<Player> players) {
        this.ID = ID;
        this.name = name;
        this.captain = captain;
        this.players = players;

        this.players.add(captain);
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public String getName() {
        return this.name;
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

    public boolean agregarJugador(Player p) {
        if (players.contains(p)) {
            System.out.println("El jugador ya está en el equipo.");
            return false;
        }
        players.add(p);
        return true;
    }
}
