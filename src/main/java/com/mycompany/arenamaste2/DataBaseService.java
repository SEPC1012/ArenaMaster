package com.mycompany.arenamaste2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataBaseService {
    private Map<String, User> usersDB;
    private List<Tournament> tournamentsDb;
    private List<Team> teamsDB;
    private List<Game> gameDB;
    private List<String> bannedDB = new ArrayList<>();

    private ServiceContainer services;

    // public DataBaseService(Map<String, User> usersDB, List<Tournament>
    // tournamentsDb, List<Team> teamsDB, List<Game> gameDB, List<String> baneadDB)
    // {
    // this.usersDB = usersDB;
    // this.tournamentsDb = tournamentsDb;
    // this.teamsDB = teamsDB;
    // this.gameDB = gameDB;
    // this.baneadDB = baneadDB;
    // }
    public DataBaseService() {
        this.usersDB = new HashMap<>();
        this.tournamentsDb = new ArrayList<>();
        this.teamsDB = new ArrayList<>();
        this.gameDB = new ArrayList<>();
        this.bannedDB = new ArrayList<>();

        // Juegos
        Game dota2 = new Game("DOTA2", "MOBA", "Juego 5 vs 5");
        Game lol = new Game("League of Legends", "MOBA", "Juego 5 vs 5");
        Game clashRoyale = new Game("Clash Royale", "Estrategia", "Juego individual 1 vs 1");
        Game valorant = new Game("Valorant", "Shooter", "Juego 5 vs 5");
        Game csgo = new Game("CS:GO", "Shooter", "Juego 5 vs 5");

        this.gameDB.add(dota2);
        this.gameDB.add(lol);
        this.gameDB.add(clashRoyale);
        this.gameDB.add(valorant);
        this.gameDB.add(csgo);

        // Admin
        Admin adminDefault = new Admin("Diego", "Neyra", "nneyra@arena", "AD0001", "nneyra123", UserRole.ADMIN);
        this.usersDB.put(adminDefault.ID, adminDefault);

        // Players
        Player p1 = new Player("Sebastian", "Pizarro", "sp@arena", "P001", "sp1012", UserRole.PLAYER, "SEPC1012", dota2,
                "Team Secret");
        Player p2 = new Player("Carlos", "Lopez", "cl@arena", "P002", "pass2", UserRole.PLAYER, "CaloX", lol, "T1");
        Player p3 = new Player("Ana", "Martinez", "am@arena", "P003", "pass3", UserRole.PLAYER, "Anita", clashRoyale,
                "sinTeam");
        Player p4 = new Player("Luis", "Gomez", "lg@arena", "P004", "pass4", UserRole.PLAYER, "Luigi", valorant,
                "Sentinels");
        Player p5 = new Player("Maria", "Torres", "mt@arena", "P005", "pass5", UserRole.PLAYER, "MariCR", csgo, "NaVi");

        // LoL / FPS Stars
        Player faker = new Player("Lee", "Sang-hyeok", "faker@t1.com", "P006", "t1win", UserRole.PLAYER, "Faker", lol,
                "T1");
        Player s1mple = new Player("Oleksandr", "Kostyliev", "s1mple@navi.com", "P007", "awpgod", UserRole.PLAYER,
                "s1mple", csgo, "NaVi");
        Player tenz = new Player("Tyson", "Ngo", "tenz@sentinels.com", "P008", "aimbot", UserRole.PLAYER, "TenZ",
                valorant, "Sentinels");

        // Dota 2 Legends
        Player miracle = new Player("Amer", "Al-Barkawi", "miracle@liquid.com", "P009", "miracle", UserRole.PLAYER,
                "Miracle-", dota2, "Team Nigma");
        Player notail = new Player("Johan", "Sundstein", "notail@og.com", "P010", "flower", UserRole.PLAYER, "N0tail",
                dota2, "OG");
        Player puppey = new Player("Clement", "Ivanov", "puppey@secret.com", "P011", "machete", UserRole.PLAYER,
                "Puppey", dota2, "Team Secret");
        Player rtz = new Player("Artour", "Babaev", "rtz@sr.com", "P012", "cliff", UserRole.PLAYER, "Arteezy", dota2,
                "Shopify");

        this.usersDB.put(p1.ID, p1);
        this.usersDB.put(p2.ID, p2);
        this.usersDB.put(p3.ID, p3);
        this.usersDB.put(p4.ID, p4);
        this.usersDB.put(p5.ID, p5);
        this.usersDB.put(faker.ID, faker);
        this.usersDB.put(s1mple.ID, s1mple);
        this.usersDB.put(tenz.ID, tenz);
        this.usersDB.put(miracle.ID, miracle);
        this.usersDB.put(notail.ID, notail);
        this.usersDB.put(puppey.ID, puppey);
        this.usersDB.put(rtz.ID, rtz);

        // Teams
        List<Player> t1Players = new ArrayList<>(Arrays.asList(p2, faker));
        Team t1 = new Team("T001", "T1", faker, t1Players);

        List<Player> naviPlayers = new ArrayList<>(Arrays.asList(p5, s1mple));
        Team navi = new Team("T002", "NaVi", s1mple, naviPlayers);

        List<Player> sentinelsPlayers = new ArrayList<>(Arrays.asList(p4, tenz));
        Team sentinels = new Team("T003", "Sentinels", tenz, sentinelsPlayers);

        // Dota 2 Teams
        List<Player> secretPlayers = new ArrayList<>(Arrays.asList(p1, puppey));
        Team secret = new Team("T004", "Team Secret", puppey, secretPlayers);

        List<Player> ogPlayers = new ArrayList<>(Arrays.asList(notail));
        Team og = new Team("T005", "OG", notail, ogPlayers);

        List<Player> nigmaPlayers = new ArrayList<>(Arrays.asList(miracle));
        Team nigma = new Team("T006", "Team Nigma", miracle, nigmaPlayers);

        this.teamsDB.add(t1);
        this.teamsDB.add(navi);
        this.teamsDB.add(sentinels);
        this.teamsDB.add(secret);
        this.teamsDB.add(og);
        this.teamsDB.add(nigma);

        // Tournaments
        // Tournament 1: Dota 2 (Open)
        Tournament torneoDota = new Tournament("TOR01", "The International 2024", dota2, ParticipantType.TEAM, false,
                16);
        torneoDota.getParticipants().add(secret);
        torneoDota.getParticipants().add(og);
        torneoDota.getParticipants().add(nigma);

        // Add history
        p1.addTournament(torneoDota);
        puppey.addTournament(torneoDota);
        notail.addTournament(torneoDota);
        miracle.addTournament(torneoDota);

        this.tournamentsDb.add(torneoDota);

        // Tournament 2: LoL Worlds (Closed/Simulated History)
        Tournament worlds = new Tournament("TOR02", "Worlds 2024", lol, ParticipantType.TEAM, true, 2);
        worlds.getParticipants().add(t1);
        faker.addTournament(worlds);
        p2.addTournament(worlds);

        // Manual Match History for Faker
        Match finalWorlds = new Match(t1, navi); // Hypothetical match
        finalWorlds.simular(); // Simulate to set score
        faker.addMatch(finalWorlds); // Add to Faker's history manually

        this.tournamentsDb.add(worlds);

        // Tournament 3: Valorant Champions
        Tournament vct = new Tournament("TOR03", "VCT Champions", valorant, ParticipantType.TEAM, false, 16);
        vct.getParticipants().add(sentinels);
        tenz.addTournament(vct);
        p4.addTournament(vct);
        this.tournamentsDb.add(vct);

        System.out.println("✔ DataBaseService inicializado con datos extendidos.");
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

    public void addGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Agregar Juego ---");

        System.out.print("Nombre del juego: ");
        String name = sc.nextLine();
        System.out.println("defiinir generp");
        String gender = sc.nextLine();
        System.out.println(" meter info");
        String info = sc.nextLine();
        Game game = new Game(name, gender, info);
        gameDB.add(game);
        System.out.println("Juego guardado.");
    }

}
