package com.mycompany.arenamaste2;

import java.util.List;
import java.util.Scanner;

public class AuthService {

    private ServiceContainer services;
    private User currentUser;

    public AuthService(ServiceContainer services) {
        this.services = services;
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void login(){
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("Inserte correo");
            String email = sc.nextLine();

            if(services.getDatabaseService().getBannedDB().contains(email)){
                System.out.println("sta cuenta fue baneada chapa tu carboncito nomas");
                continue;
            }
            System.out.println("Inserte contraseña");
            String password = sc.nextLine();

            User user = null;
            for (User u :services.getDatabaseService().getUsersDB().values()){
                if(u.getEmail().equals(email)&&u.getPassword().equals(password)){
                    user = u;
                    break;
                }
            }
            if(user==null){
                System.out.println("Correo o contraseña incorrectos.");
                continue;
            }

            currentUser = user;
            break;

        }
        switch (currentUser.rol){
            case ADMIN -> ((Admin) currentUser).showMenu(services);
            case PLAYER -> ((Player) currentUser).showMenu(services);
        }
    }

    public void registerUser(){
        Scanner sc = new Scanner(System.in);

        System.out.println("registrar usuario");
        System.out.println("insertar nombre");
        String name= sc.nextLine();
        System.out.println("insertar apellido");
        String surname= sc.nextLine();
        System.out.println(" insertar email");
        String email= sc.nextLine();

        if (services.getDatabaseService().getBannedDB().contains(email)){
            System.out.println(" baneado rctmr");
            return;
        }
        for (User u: services.getDatabaseService().getUsersDB().values()){
            if(u.email.equals(email)){
                System.out.println(" ya sta 00");
                return;
            }
        }
        System.out.println("inserte conrtra");
        String password = sc.nextLine();
        System.out.println("inserte nickname");
        String nickname = sc.nextLine();

        List<Game> games    = services.getDatabaseService().getGameDB();
        if (games.isEmpty()){
            System.out.println("nohay juegos gg");
            return;
        }
        System.out.println("escoge un jeugo ");

        for (int i = 0; i < games.size(); i++) {
            System.out.println((i + 1) + ". " + games.get(i).getTitle());
        }
        int option;
        do{
            System.out.println("escoge mrd");
            option = sc.nextInt();
            sc.nextLine();
        }while(option<1||option> games.size());

        Game selectedGame = games.get(option-1);

        String ID = "P" +String.format("%04d",services.getDatabaseService().getUsersDB().size()+1);

        Player nuevo = new Player(name,surname,email,ID,password,UserRole.PLAYER,nickname,selectedGame,"sinTeam");
        nuevo.setServices(services);
        services.getDatabaseService().getUsersDB().put(email,nuevo);
        System.out.println("registro exitoso");
    }
    public void logout(){
        if (currentUser != null){
            System.out.println("sesion cerrada: "+currentUser.getName());
        }
        currentUser=null;

    }
}
