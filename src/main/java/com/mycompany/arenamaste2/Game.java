package com.mycompany.arenamaste2;


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
        ServiceContainer services = ArenaMaste2.getServices();
        return services.getDatabaseService().getTournamentsDb().stream()
                .filter(t -> t.getGame().equals(this))
                .collect(Collectors.toList());
    }

    public ServiceContainer getServices() {
        return services;
    }

    public void setServices(ServiceContainer services) {
        this.services = services;
    }
}
