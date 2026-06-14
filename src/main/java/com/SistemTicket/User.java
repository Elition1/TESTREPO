package com.SistemTicket;

//Kelas Pembuatan User Objek
public abstract class User 
{
    //Atribut objek User
    protected String name;
    protected String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
