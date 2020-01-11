package com.raddiwala.web.repository;

import com.raddiwala.web.model.ProductQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantity,Long> {
}
