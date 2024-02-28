package com.globant.project.foodAplication.model.category;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String HAMBURGERS_AND_HOTDOGS = "HAMBURGERS_AND_HOTDOGS";
    private  String CHICKEN= "CHICKEN";
    private  String FISH = "FISH";
    private  String MEATS = "MEATS";
    private  String DESSERTS  = "DESSERTS";;
    private  String VEGAN_FOOD = "VEGAN_FOOD";
    private  String KIDS_MEALS = "KIDS_MEALS";

}
