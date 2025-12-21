package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.List;

public class Round implements Navigable {
    private String name;
    private List<Match> matches;

    public Round(String name, List<Match> matches) {
        this.name = name;
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Navigable> getChildren() {
        return new ArrayList<>(matches);
    }
}
