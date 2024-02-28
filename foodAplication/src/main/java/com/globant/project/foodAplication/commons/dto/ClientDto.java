package com.globant.project.foodAplication.commons.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ClientDto {

    private String document;
    private String name;
    private String email;
    private String phone;
    private String deliveryAddress;



}
