package com.globant.project.foodAplication.commons.dto;

import com.globant.project.foodAplication.model.client.Client;
import com.globant.project.foodAplication.model.product.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class OrderDto {

    private Product product_id;
    private Client client_id;
    private Integer quantity;
    private String extraInformation;
    private Date creationDateTime;
    private Date deleveryDate;
    private Double tax;
    private Double grandTotal;
    private Double subTotal;
    private Boolean isDelivered;
}
