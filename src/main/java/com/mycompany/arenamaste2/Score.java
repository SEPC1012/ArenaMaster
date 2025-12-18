package com.mycompany.arenamaste2;

public class Score {
    private int puntosP1;
    private int puntosP2;
    private Participant ganador;

    public void setScore(int puntosP1, int puntosP2, Participant p1, Participant p2) {
        this.puntosP1 = puntosP1;
        this.puntosP2 = puntosP2;
        this.ganador = puntosP1 > puntosP2 ? p1 : p2;
    }

    public Participant getGanador() {
        return ganador;
    }

    public int getPuntosP1() {
        return puntosP1;
    }

    public void setPuntosP1(int puntosP1) {
        this.puntosP1 = puntosP1;
    }

    public int getPuntosP2() {
        return puntosP2;
    }

    public void setPuntosP2(int puntosP2) {
        this.puntosP2 = puntosP2;
    }

    public void setGanador(Participant ganador){
        this.ganador= ganador;
    }

    @Override
    public String toString() {
        return puntosP1 + " - " + puntosP2;
    }
}
