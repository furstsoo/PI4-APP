package com.project.entity;

public class Retorno {
    private String dsRetorno;
    private int id;

    public Retorno() {
    }

    public Retorno(String dsRetorno, int id) {
        this.dsRetorno = dsRetorno;
        this.id = id;
    }

    public String getDsRetorno() {
        return dsRetorno;
    }

    public void setDsRetorno(String dsRetorno) {
        this.dsRetorno = dsRetorno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
