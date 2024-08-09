package com.alissonfgc.casacerta.dto;

import com.alissonfgc.casacerta.entities.Client;

import java.io.Serializable;

public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String individualRegistration;
    private String password;

    public ClientDTO() {}

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.phoneNumber = client.getPhoneNumber();
        this.individualRegistration = client.getIndividualRegistration();
        this.password = client.getPassword();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIndividualRegistration() {
        return individualRegistration;
    }

    public void setIndividualRegistration(String individualRegistration) {
        this.individualRegistration = individualRegistration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

