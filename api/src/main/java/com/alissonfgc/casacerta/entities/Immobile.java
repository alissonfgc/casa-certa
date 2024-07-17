package com.alissonfgc.casacerta.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Table(name = "tb_immobile")
@Entity(name = "immobile")
public class Immobile  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate creationDate;
    private String neighborhood;
    private String city;
    private String state;
    private Double total_area;
    private String longitude;
    private String latitude;
    private String type;
    private String postcode;
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    public Immobile() {}

    public Immobile(User seller, String imageURL, String postcode, String type, String latitude, String longitude, Double total_area, String state, String city, String neighborhood, LocalDate creationDate, String description, String title, Long id) {
        this.seller = seller;
        this.imageURL = imageURL;
        this.postcode = postcode;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.total_area = total_area;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.creationDate = creationDate;
        this.description = description;
        this.title = title;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getTotal_area() {
        return total_area;
    }

    public void setTotal_area(Double total_area) {
        this.total_area = total_area;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Immobile immobile = (Immobile) o;
        return Objects.equals(id, immobile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}