package com.alissonfgc.casacerta.dto;

import com.alissonfgc.casacerta.entities.Admin;

import java.io.Serializable;

public class AdminDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String registrationNumber;
    private String password;

    public AdminDTO() {
    }

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.email = admin.getEmail();
        this.phoneNumber = admin.getPhoneNumber();
        this.registrationNumber = admin.getRegistrationNumber();
        this.password = admin.getPassword();
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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
