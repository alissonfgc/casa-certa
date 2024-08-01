package com.alissonfgc.casacerta.repository;

import com.alissonfgc.casacerta.entities.Immobile;
import com.alissonfgc.casacerta.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImmobileRepository extends JpaRepository<Immobile, Long> {

    List<Immobile> findByNeighborhoodContainingIgnoreCase(String neighborhood);

    List<Immobile> findImmobileByCityContainingIgnoreCase(String city);

    List<Immobile> findByStateContainingIgnoreCase(String state);

    List<Immobile> findByTypeContainingIgnoreCase(String type);

    List<Immobile> findBySeller(Seller seller);

    //	SELECT * FROM Customers
    //WHERE CustomerName LIKE '%a';

}
