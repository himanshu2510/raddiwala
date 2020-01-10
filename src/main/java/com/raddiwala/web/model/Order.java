package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order extends ProductAuditable {
    @ManyToMany(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<ProductQuantity> map = new ArrayList<>();

    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private Buyer buyer;
}
