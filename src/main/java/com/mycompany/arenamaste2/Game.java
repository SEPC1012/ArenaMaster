package com.mycompany.arenamaste2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game implements Navigable{
    private String title;
    private String gender;
    private String information;

    private ServiceContainer services;

    public Game(String title, String gender, String information,ServiceContainer services) {
        this.title = title;
        this.gender = gender;
        this.information = information;
        this.services = services;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public List<Navigable> getChildren() {
        if (this.services == null) return new ArrayList<>();

        List<Navigable> children = new ArrayList<>();

        // 1. Agregar Torneos de este juego
        children.addAll(services.getDatabaseService().getTournamentsDb().stream()
                .filter(t -> t.getGame().getTitle().equalsIgnoreCase(this.title))
                .collect(Collectors.toList()));

        // 2. Agregar Jugadores que juegan este juego
        children.addAll(services.getDatabaseService().getUsersDB().values().stream()
                .filter(u -> u instanceof Player)
                .map(u -> (Player) u)
                .filter(p -> p.getGame() != null && p.getGame().getTitle().equalsIgnoreCase(this.title))
                .collect(Collectors.toList()));

        return children;
    }

    public ServiceContainer getServices() {
        return services;
    }

    public void setServices(ServiceContainer services) {
        this.services = services;
    }
}
