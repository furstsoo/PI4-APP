package com.project.entity;

import android.app.Application;

public class GlobalUser  extends Application {

    private int id;
    private String name;
    private String email;
    private String password;
    private String apartment;
    private String block;
    private String typeUser;

    public GlobalUser(int id, String name, String email, String password, String apartment, String block, String typeUser) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.apartment = apartment;
        this.block = block;
        this.typeUser = typeUser;
    }

    public GlobalUser() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
