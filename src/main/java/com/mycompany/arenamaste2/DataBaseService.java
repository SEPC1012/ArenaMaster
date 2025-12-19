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
    private List<String> bannedDB = new ArrayList<>();

    private ServiceContainer services;

    //public DataBaseService(Map<String, User> usersDB, List<Tournament> tournamentsDb, List<Team> teamsDB, List<Game> gameDB, List<String> baneadDB) {
      //  this.usersDB = usersDB;
        //this.tournamentsDb = tournamentsDb;
        //this.teamsDB = teamsDB;
        //this.gameDB = gameDB;
        //this.baneadDB = baneadDB;
    //}
    public DataBaseService() {
        this.usersDB = new HashMap<>();
        this.tournamentsDb = new ArrayList<>();
        this.teamsDB = new ArrayList<>();
        this.gameDB = new ArrayList<>();
        this.bannedDB = new ArrayList<>();

        Game dota2 = new Game("DOTA2", "MOBA", "Juego 5 vs 5",services);
        Game lol = new Game("League of Legends", "MOBA", "Juego 5 vs 5",services);
        Game clashRoyale = new Game("Clash Royale", "Estrategia", "Juego individual 1 vs 1",services);
        Game valorant = new Game("Valorant", "Shooter", "Juego 5 vs 5",services);
        Game csgo = new Game("CS:GO", "Shooter", "Juego 5 vs 5",services);

        this.gameDB.add(dota2);
        this.gameDB.add(lol);
        this.gameDB.add(clashRoyale);
        this.gameDB.add(valorant);
        this.gameDB.add(csgo);

        Admin adminDefault = new Admin("Diego", "Neyra", "nneyra@arena", "AD0001", "nneyra123", UserRole.ADMIN);
        Player player1 = new Player("Sebastian", "Pizarro", "sp@arena", "P001", "sp1012", UserRole.PLAYER, "SEPC1012", dota2, "sinTeam");
        Player player2 = new Player("Carlos", "Lopez", "cl@arena", "P002", "pass2", UserRole.PLAYER, "CaloX", lol, "sinTeam");
        Player player3 = new Player("Ana", "Martinez", "am@arena", "P003", "pass3", UserRole.PLAYER, "Anita", clashRoyale, "sinTeam");
        Player player4 = new Player("Luis", "Gomez", "lg@arena", "P004", "pass4", UserRole.PLAYER, "Luigi", valorant, "sinTeam");
        Player player5 = new Player("Maria", "Torres", "mt@arena", "P005", "pass5", UserRole.PLAYER, "MariCR", csgo, "sinTeam");

        this.usersDB.put(adminDefault.ID, adminDefault);
        this.usersDB.put(player1.ID, player1);
        this.usersDB.put(player2.ID, player2);
        this.usersDB.put(player3.ID, player3);
        this.usersDB.put(player4.ID, player4);
        this.usersDB.put(player5.ID, player5);

        Tournament torneoDota = new Tournament("T001","Torneo Dota Inicial" ,dota2, ParticipantType.TEAM,false,8); // permite equipos


        this.tournamentsDb.add(torneoDota);

        System.out.println("✔ DataBaseService inicializado con usuarios, juegos y torneo por defecto");
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

    public List<String> getBannedDB() {
        return bannedDB;
    }

    public void setBannedDB(List<String> bannedDB) {
        this.bannedDB = bannedDB;
    }
}
