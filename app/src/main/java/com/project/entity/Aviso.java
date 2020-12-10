package com.project.entity;

public class Aviso {

    private int id;
    private String title;
    private String message;
    private int cd_user;

    public Aviso(){ }

    public Aviso(int id, String title, String message, int cd_user) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.cd_user = cd_user;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public int getCd_user() {
        return cd_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCd_user(int cd_user) {
        this.cd_user = cd_user;
    }
}
