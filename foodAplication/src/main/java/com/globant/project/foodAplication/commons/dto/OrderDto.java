package com.globant.project.foodAplication.commons.dto;

import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.model.product.ProductEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class OrderDto {

    private Long id;
    private ProductEntity productEntity;
    private ClientEntity clientEntity;
    private Integer quantity;
    private String extraInformation;
    private Date creationDateTime;
    private Date deleveryDate;
    private Double tax;
    private Double grandTotal;
    private Double subTotal;
    private Boolean isDelivered;
}
