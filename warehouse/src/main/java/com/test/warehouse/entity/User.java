package com.test.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "role")
    private String role;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "date_created")
    private long dateCreated;

    @Column(name = "date_modified")
    private Long dateModified;
}
