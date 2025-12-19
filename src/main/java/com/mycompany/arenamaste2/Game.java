package com.mycompany.arenamaste2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game implements Navigable{
    private String title;
    private String gender;
    private String information;
    private List<Tournament> tournaments = new ArrayList<>();

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

    @Override
    public String getName() {
        return title;
    }

    @Override
    public List<Navigable> getChildren() {
        return new ArrayList<>(tournaments);
    }



}
