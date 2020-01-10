package com.raddiwala.web.repository;

import com.raddiwala.web.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByBuyer(Long buyerId);
}
