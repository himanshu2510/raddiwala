package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

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
    private List<ProductQuantity> prodQty = new ArrayList<>();

    @Getter
    @Setter
    private Long userId;

    @Getter
    @Setter
    private Long buyerId;
//    @RequestBody String input.get("key1")
}
