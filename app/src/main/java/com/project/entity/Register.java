package com.project.entity;

public class Register {
    private String register_day;
    private int cd_user;

    public Register() {
    }

    public Register(String register_day, int cd_user) {
        this.register_day = register_day;
        this.cd_user = cd_user;
    }

    public String getRegister_day() {
        return register_day;
    }

    public void setRegister_day(String register_day) {
        this.register_day = register_day;
    }

    public int getCd_user() {
        return cd_user;
    }

    public void setCd_user(int cd_user) {
        this.cd_user = cd_user;
    }
}
