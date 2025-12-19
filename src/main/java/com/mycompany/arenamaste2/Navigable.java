package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.List;

public interface Navigable {
    String getName();
    List<Navigable> getChildren();

}
/*
public class Tournament implements Navigable, Viewable {

    private List<Round> rounds;
    private List<Team> teams;

    @Override
    public String getName() {
        return nombre;
    }

    @Override
    public List<Navigable> getChildren() {
        List<Navigable> children = new ArrayList<>();
        children.addAll(rounds);
        children.addAll(teams);
        return children;
    }

    @Override
    public void showInfo() {
        System.out.println("Torneo: " + nombre);
        System.out.println("Rondas: " + rounds.size());
        System.out.println("Equipos: " + teams.size());
    }
}
public class Round implements Navigable, Viewable {

    private int number;
    private List<Match> matches;

    @Override
    public String getName() {
        return "Ronda " + number;
    }

    @Override
    public List<Navigable> getChildren() {
        return new ArrayList<>(matches);
    }

    @Override
    public void showInfo() {
        System.out.println("Ronda " + number);
        System.out.println("Matches: " + matches.size());
    }
}
public class Match implements Navigable, Viewable {

    private Team teamA;
    private Team teamB;
    private String result;

    @Override
    public String getName() {
        return teamA.getName() + " vs " + teamB.getName();
    }

    @Override
    public List<Navigable> getChildren() {
        return List.of(); // hoja
    }

    @Override
    public void showInfo() {
        System.out.println(getName());
        System.out.println("Resultado: " + result);
    }
}
public class Team implements Navigable, Viewable {

    private List<Match> matches;
    private List<Tournament> tournaments;

    @Override
    public String getName() {
        return nombre;
    }

    @Override
    public List<Navigable> getChildren() {
        List<Navigable> children = new ArrayList<>();

        matches.stream()
               .limit(5)
               .forEach(children::add);

        tournaments.stream()
                   .limit(5)
                   .forEach(children::add);

        return children;
    }

    @Override
    public void showInfo() {
        System.out.println("Equipo: " + nombre);
        System.out.println("Últimos matches:");
        matches.stream().limit(5).forEach(System.out::println);
    }
}
void mostrar(Navigable item) {

    if (item instanceof Viewable v) {
        v.showInfo();
    }

    List<Navigable> hijos = item.getChildren();
    if (hijos.isEmpty()) return;

    for (int i = 0; i < Math.min(10, hijos.size()); i++) {
        System.out.println((i + 1) + ". " + hijos.get(i).getName());
    }
}

 */
