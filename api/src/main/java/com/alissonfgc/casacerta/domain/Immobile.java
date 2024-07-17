package com.alissonfgc.casacerta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "immobile")
@Entity(name = "immobile")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Immobile{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

    private
}