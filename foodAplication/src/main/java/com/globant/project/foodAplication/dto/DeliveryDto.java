package com.globant.project.foodAplication.dto;

import com.globant.project.foodAplication.model.Client;

public class DeliveryDto {

    private Long product_id;
    private Client client_id;
    private Integer quantity;
    private String extraInformation;
    public DeliveryDto() {
    }

    public DeliveryDto(Long product_id, Client client_id, Integer quantity, String extraInformation) {
        this.product_id = product_id;
        this.client_id = client_id;
        this.quantity = quantity;
        this.extraInformation = extraInformation;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }
}
