package com.alissonfgc.casacerta.repository;

import com.alissonfgc.casacerta.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findByEmail(String email);
}
