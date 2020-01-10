package com.raddiwala.web.repository;

import com.raddiwala.web.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyerRepository extends JpaRepository<Buyer,Long> {
    Buyer findBuyerByUsername(String username);

    List<Buyer> findAllByCity(String city);
}
