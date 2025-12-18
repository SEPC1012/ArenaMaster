package com.mycompany.arenamaste2;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Tournament {

    private String name;
    private Game game;
    private boolean allowsTeams;
    private boolean closed;
    private List<Participant> participants;

    public Tournament(String name, Game game, boolean allowsTeams) {
        this.name = name;
        this.game = game;
        this.allowsTeams = allowsTeams;
        this.closed = false;
        this.participants = new ArrayList<>();
    }



    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isAllowsTeams() {
        return allowsTeams;
    }

    public void setAllowsTeams(boolean allowsTeams) {
        this.allowsTeams = allowsTeams;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public void addParticipants(Participant p){
        System.out.println("In development");
    }

}
