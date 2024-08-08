package com.alissonfgc.casacerta.repository;

import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImmobileRepository extends JpaRepository<Immobile, Long> {

    List<Immobile> findByStateOrCityOrTypeIgnoreCase(String state, String city, String type);

    List<Immobile> findByStateAndCityAndTypeIgnoreCase(String state, String city, String type);

    List<Immobile> findByStateAndCityIgnoreCase(String state, String city);

    List<Immobile> findByStateAndTypeIgnoreCase(String state, String type);

    List<Immobile> findByCityAndTypeIgnoreCase(String city, String type);

    List<Immobile> findBySeller(Seller seller);
}
