package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tournament implements Navigable {
    private String ID;
    private String name;
    private Game game;
    private ParticipantType allowedParticipantType;
    private boolean closed;
    private int maxParticipant;
    private List<Participant> participants;
    private List<Round> rounds;
    private List<Match> matches;

    private Map<Participant, Integer> standings = new HashMap<>();

    public Tournament(String ID, String name, Game game, ParticipantType allowedParticipantType, boolean closed,
            int maxParticipant) {
        this.ID = ID;
        this.name = name;
        this.game = game;
        this.allowedParticipantType = allowedParticipantType;
        this.closed = false;
        this.maxParticipant = maxParticipant;

        this.participants = new ArrayList<>();
        this.rounds = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void setPosition(Participant p, int position) {
        standings.put(p, position);
        p.addTournament(this); // Actualiza historial de los últimos 5 torneos
    }

    public Integer getPosition(Participant p) {
        return standings.get(p);
    }

    public void addParticipant(Participant p) {
        if (!participants.contains(p) && !isFull()) {
            participants.add(p);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public List<Navigable> getChildren() {
        return new ArrayList<>(rounds);
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

    public ParticipantType getAllowedParticipantType() {
        return allowedParticipantType;
    }

    public int getMaxParticipant() {
        return maxParticipant;
    }

    public void setMaxParticipant(int maxParticipant) {
        this.maxParticipant = maxParticipant;
    }

    public void setAllowedParticipantType(ParticipantType allowedParticipantType) {
        this.allowedParticipantType = allowedParticipantType;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public String getID() {
        return ID;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public boolean isFull() {
        return participants.size() >= maxParticipant;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
