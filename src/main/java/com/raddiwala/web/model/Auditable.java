package com.raddiwala.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt","updatedAt"}, allowGetters = true)
public abstract class Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false,updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date createdAt = new Date();

    @Column(nullable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date updatedAt = new Date();

    @Getter
    @Setter
    @NotBlank
    private String username;

    @Getter
    @Setter
    @NotBlank
    private String password;



//    private String house;
//    private String area;
//    private String city;
//    private String state;
//    private Integer pincode;

}
