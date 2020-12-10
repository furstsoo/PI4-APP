package com.project.entity;

public class Order {
    private int id_order;
    private String addressee;
    private String status;
    private String apartment;
    private String block;
    private String dt_delivery;
    private String dt_pickup;
    private int cd_user;
    private String remetente;
    private String descricao;

    public Order(int id_order, String addressee, String status, String apartment, String block, String dt_delivery, String dt_pickup, int cd_user, String remetente, String descricao) {
        this.id_order = id_order;
        this.addressee = addressee;
        this.status = status;
        this.apartment = apartment;
        this.block = block;
        this.dt_delivery = dt_delivery;
        this.dt_pickup = dt_pickup;
        this.cd_user = cd_user;
        this.remetente = remetente;
        this.descricao = descricao;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Order() {}

    public Order(String addressee, String status, String apartment, String block, String dt_delivery, int cd_user) {
        this.addressee = addressee;
        this.status = status;
        this.apartment = apartment;
        this.block = block;
        this.dt_delivery = dt_delivery;
        this.cd_user = cd_user;
    }

    public Order(int id_order, String addressee, String status, String apartment, String block, String dt_delivery, String dt_pickup, int cd_user) {
        this.id_order = id_order;
        this.addressee = addressee;
        this.status = status;
        this.apartment = apartment;
        this.block = block;
        this.dt_delivery = dt_delivery;
        this.dt_pickup = dt_pickup;
        this.cd_user = cd_user;
    }

    public Order(String addressee, String status, String apartment, String block, String dt_delivery, int cd_user, String remetente, String descricao) {
        this.addressee = addressee;
        this.status = status;
        this.apartment = apartment;
        this.block = block;
        this.dt_delivery = dt_delivery;
        this.cd_user = cd_user;
        this.remetente = remetente;
        this.descricao = descricao;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDt_delivery() {
        return dt_delivery;
    }

    public void setDt_delivery(String dt_delivery) {
        this.dt_delivery = dt_delivery;
    }

    public String getDt_pickup() {
        return dt_pickup;
    }

    public void setDt_pickup(String dt_pickup) {
        this.dt_pickup = dt_pickup;
    }

    public int getCd_user() {
        return cd_user;
    }

    public void setCd_user(int cd_user) {
        this.cd_user = cd_user;
    }
}
