package com.mycompany.arenamaste2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseService {
    private Map<String, User> usersDB;
    private List<Tournament> tournamentsDb;
    private List<Team> teamsDB;
    private List<Game> gameDB;
    private List<String> baneadDB = new ArrayList<>();

    public DataBaseService(Map<String, User> usersDB, List<Tournament> tournamentsDb, List<Team> teamsDB, List<Game> gameDB, List<String> baneadDB) {
        this.usersDB = usersDB;
        this.tournamentsDb = tournamentsDb;
        this.teamsDB = teamsDB;
        this.gameDB = gameDB;
        this.baneadDB = baneadDB;
    }
    public DataBaseService() {
        this.usersDB = new HashMap<>();
        this.tournamentsDb = new ArrayList<>();
        this.teamsDB = new ArrayList<>();
        this.gameDB = new ArrayList<>();
        this.baneadDB = new ArrayList<>();

        System.out.println("✔ DataBaseService inicializado");
    }

    public Map<String, User> getUsersDB() {
        return usersDB;
    }

    public void setUsersDB(Map<String, User> usersDB) {
        this.usersDB = usersDB;
    }

    public List<Tournament> getTournamentsDb() {
        return tournamentsDb;
    }

    public void setTournamentsDb(List<Tournament> tournamentsDb) {
        this.tournamentsDb = tournamentsDb;
    }

    public List<Team> getTeamsDB() {
        return teamsDB;
    }

    public void setTeamsDB(List<Team> teamsDB) {
        this.teamsDB = teamsDB;
    }

    public List<Game> getGameDB() {
        return gameDB;
    }

    public void setGameDB(List<Game> gameDB) {
        this.gameDB = gameDB;
    }

    public List<String> getBaneadDB() {
        return baneadDB;
    }

    public void setBaneadDB(List<String> baneadDB) {
        this.baneadDB = baneadDB;
    }
}
