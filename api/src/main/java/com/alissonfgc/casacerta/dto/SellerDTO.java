package com.alissonfgc.casacerta.dto;

import com.alissonfgc.casacerta.entities.Seller;

import java.io.Serializable;

public class SellerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String registrationNumber;
    private String password;
    private String companyName;

    public SellerDTO() {
    }

    public SellerDTO(Seller seller) {
        id = seller.getId();
        name = seller.getName();
        email = seller.getEmail();
        phoneNumber = seller.getPhoneNumber();
        registrationNumber = seller.getRegistrationNumber();
        password = seller.getPassword();
        companyName = seller.getCompanyName();
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}