package com.mycompany.arenamaste2;

import java.util.Scanner;

public class AuthService {

    private ServiceContainer services;
    private User usuarioActual;

    public AuthService(DataBaseService services) {
        this.services = services;
        this.usuarioActual = null;
    }

    public User getUsuarioActual() {
        return usuarioActual;
    }

    public void login(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserte correo");
        String email = sc.nextLine();
        System.out.println("Inserte contraseña");
        String password = sc.nextLine();
        System.out.println("In development");
    }

    public void registerUser(){
        System.out.println(" In development");

    }
    public void logout(){
        System.out.println(" In development");
    }
}
