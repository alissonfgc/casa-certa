package com.alissonfgc.casacerta.dto;

import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.entities.Seller;

import java.io.Serializable;
import java.time.LocalDate;

public class ImmobileDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String description;
    private LocalDate creationDate;
    private String neighborhood;
    private String city;
    private String state;
    private Double totalArea;
    private String longitude;
    private String latitude;
    private String type;
    private String postcode;
    private String imageURL;
    private VendorDTO vendor;

    public ImmobileDTO() {}

    public ImmobileDTO(Immobile immobile) {
        this.id = immobile.getId();
        this.title = immobile.getTitle();
        this.description = immobile.getDescription();
        this.creationDate = immobile.getCreationDate();
        this.neighborhood = immobile.getNeighborhood();
        this.city = immobile.getCity();
        this.state = immobile.getState();
        this.totalArea = immobile.getTotalArea();
        this.longitude = immobile.getLongitude();
        this.latitude = immobile.getLatitude();
        this.type = immobile.getType();
        this.postcode = immobile.getPostcode();
        this.imageURL = immobile.getImageURL();
        this.vendor = new VendorDTO(immobile.getSeller());
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

    public Double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Double totalArea) {
        this.totalArea = totalArea;
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

    public VendorDTO getVendor() {
        return vendor;
    }

    public void setVendor(Seller seller) {
        this.vendor = new VendorDTO(seller);
    }
}
