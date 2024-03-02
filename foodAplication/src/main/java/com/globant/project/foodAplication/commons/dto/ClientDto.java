package com.globant.project.foodAplication.commons.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClientDto {

    private Integer id;
    private String document;
    private String name;
    private String email;
    private String phone;
    private String deliveryAddress;



}
