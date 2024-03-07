package com.globant.project.foodAplication.commons.dto;

import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.model.product.ProductEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
public class OrderDto {

    private ProductEntity product;
    private ClientEntity client;
    private Integer quantity;
    private String extraInformation;
    private LocalDateTime creationDateTime;
    private LocalDateTime deliveryDate;
    private Double tax;
    private Double grandTotal;
    private Double subTotal;
    private Boolean isDelivered = false;
    private UUID uuid;
}
