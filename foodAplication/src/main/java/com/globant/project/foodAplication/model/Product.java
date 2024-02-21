package com.globant.project.foodAplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fantasy_name")
    private String fantasyName;

    private Category category;

    private String description;

    private String price;

    private Boolean available;
}
