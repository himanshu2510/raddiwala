package com.raddiwala.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class Invoice extends ProductAuditable {
    @Getter
    @Setter
    @Column(name = "orders")
    private Long orderId;

    @Getter
    @Setter
    private Long invoiceAmount;

}
