package com.globant.project.foodAplication.commons.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String document;
    private String name;
    private String email;
    private String phone;
    private String deliveryAddress;
    private Boolean isABoolean = true;
}
