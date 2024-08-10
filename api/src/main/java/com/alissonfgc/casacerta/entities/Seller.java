package com.alissonfgc.casacerta.entities;


import com.alissonfgc.casacerta.entities.roles.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "tb_seller")
@Entity(name = "seller")
public class Seller extends User {

    @Column(nullable = false)
    private String companyName;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private List<Immobile> immobiles = new ArrayList<>();

    public Seller() {
    }

    public Seller(String id, String name, String email, String phoneNumber, String registrationNumber, String password, String companyName) {
        super(id, name, email, phoneNumber, registrationNumber, password);
        this.companyName = companyName;
        this.role = UserRole.SELLER;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Immobile> getImmobiles() {
        return immobiles;
    }
}