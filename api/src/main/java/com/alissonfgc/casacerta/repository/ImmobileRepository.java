package com.alissonfgc.casacerta.repository;

import com.alissonfgc.casacerta.entities.Immobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmobileRepository extends JpaRepository<Immobile, Long> {

    Immobile findByNeighborhood(String neighborhood);
    Immobile findImmobileByCity(String city);
    Immobile findByState(String state);
    Immobile findByType(String type);



    //	SELECT * FROM Customers
    //WHERE CustomerName LIKE '%a';

}
