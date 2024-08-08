package com.alissonfgc.casacerta.repository;

import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImmobileRepository extends JpaRepository<Immobile, Long> {

    List<Immobile> findByStateOrCityOrTypeIgnoreCase(String state, String city, String type);

    List<Immobile> findByStateAndCityAndTypeIgnoreCase(String state, String city, String type);

    List<Immobile> findByStateAndCityIgnoreCase(String state, String city);

    List<Immobile> findByStateAndTypeIgnoreCase(String state, String type);

    List<Immobile> findByCityAndTypeIgnoreCase(String city, String type);

    @Query("select i from immobile i where upper(trim(i.title)) like %?1% or upper(trim(i.description)) like %?2% or upper(trim(i.neighborhood)) like %?3%")
    List<Immobile> fullSearch(String title, String description, String neighborhood);

    List<Immobile> findBySeller(Seller seller);
}
