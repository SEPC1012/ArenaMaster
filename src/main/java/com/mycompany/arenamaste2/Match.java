package com.mycompany.arenamaste2;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Match implements Navigable{
    private Participant p1;
    private Participant p2;
    private Score score;

    private boolean played = false;

    private Map<Participant, Boolean> results = new HashMap<>();

    public Match(Participant p1, Participant p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.score = new Score();
    }

    public Participant simular() {
        int puntos1 = (int) (Math.random() * 10);
        int puntos2 = (int) (Math.random() * 10);
        score.setScore(puntos1, puntos2, p1, p2);

        Participant ganador = score.getGanador();

        results.put(p1, ganador == p1);
        results.put(p2, ganador == p2);

        p1.addMatch(this);
        p2.addMatch(this);

        this.played = true;

        return ganador;
    }
    public Boolean getResult(Participant p) {
        return results.get(p);
    }

    @Override
    public String toString() {
        if (!played || score.getGanador() == null) {
            return p1.getName() + " vs " + p2.getName() + " [Pendiente]";
        }

        return p1.getName() + " vs " + p2.getName()
                + " → Ganador: " + score.getGanador().getName()
                + " (" + score.getPuntosP1() + " - " + score.getPuntosP2() + ")";
    }

    @Override
    public String getName() {
        return p1.getName() + " vs " + p2.getName();
    }

    @Override
    public List<Navigable> getChildren() {
        return Collections.emptyList();
    }

}

