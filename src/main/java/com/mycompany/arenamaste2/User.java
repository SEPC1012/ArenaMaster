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
    protected UserRole rol;

    public User(String name, String surname, String email, String ID, String password, UserRole rol) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.ID = ID;
        this.password = password;
        this.rol = rol;
    }

    public abstract void showMenu(ServiceContainer services);

    public ServiceContainer getServices() {
        return services;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRol() {
        return rol;
    }
}
