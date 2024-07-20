package com.test.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "date_created")
    private long dateCreated;

    @Column(name = "date_modified")
    private Long dateModified;
}
