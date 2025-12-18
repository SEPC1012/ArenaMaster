package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Match> matches;

    public Round(List<Match> matches) {
        this.matches = matches;
    }

    public List<Participant> simularRonda() {
        List<Participant> ganadores = new ArrayList<>();
        for (Match m : matches) {
            ganadores.add(m.simular());
            System.out.println(m);
        }
        return ganadores;
    }
}

