package com.globant.project.foodAplication.commons.dto;

import com.globant.project.foodAplication.model.category.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class ProductDto {

    private String fantasyName;
    private Category category;
    private String description;
    private Double price;
    private Boolean available = true;
    private UUID uuid;

}
