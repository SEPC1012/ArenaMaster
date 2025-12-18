package com.mycompany.arenamaste2;

public class Match {
    private Participant p1;
    private Participant p2;
    private Score score;

    public Match(Participant p1, Participant p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.score = new Score();
    }

    public Participant simular() {
    int puntos1 = (int)(Math.random() * 10);
    int puntos2 = (int)(Math.random() * 10);
    score.setScore(puntos1, puntos2, p1, p2);
    return score.getGanador();
    }

    @Override
public String toString() {
    String detallePartida = p1.getName() + " vs " + p2.getName()
                          + " → Ganador: " + score.getGanador().getName()
                          + " (" + score.getPuntosP1() + " - " + score.getPuntosP2() + ")";

    return detallePartida;
}

}

