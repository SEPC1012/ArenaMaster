package com.mycompany.arenamaste2;

public abstract class User {

    protected ServiceContainer services;

    public void setServices(ServiceContainer services) {
        this.services = services;
    }

    protected String name;
    protected String surname;
    protected String email;
    protected String ID;
    protected String password;
    protected String rol;

    public User(String name, String surname, String email, String ID, String password, String rol) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.ID = ID;
        this.password = password;
        this.rol = rol;
    }

    public abstract void showMenu(ServiceContainer services);

}
