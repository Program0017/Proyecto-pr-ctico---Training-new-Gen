package com.globant.project.foodAplication.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globant.project.foodAplication.model.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique = true, length = 36, columnDefinition = "BINARY(16)")
    private UUID uuid = UUID.randomUUID();

    @Column(name = "fantasy_name")
    private String fantasyName;

    private Category category;

    private String description;

    private Double price;

    private Boolean available;

}
