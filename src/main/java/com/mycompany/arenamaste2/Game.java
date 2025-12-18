package com.mycompany.arenamaste2;


public class Game {
    private String title;
    private String gender;
    private String information;

    public Game(String title, String gender, String information) {
        this.title = title;
        this.gender = gender;
        this.information = information;
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
}
