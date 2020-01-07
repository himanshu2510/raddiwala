package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order extends Auditable {
    @Getter
    @Setter
    private Map<Product,Integer> prodQtyMap = new HashMap<>();

    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private Buyer buyer;
}
