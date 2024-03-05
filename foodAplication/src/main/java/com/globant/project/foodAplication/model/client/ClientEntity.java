package com.globant.project.foodAplication.model.client;


import jakarta.persistence.*;
import lombok.*;
@Builder
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients", indexes = {
        @Index(columnList = "document", unique = true),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "phone", unique = true)
})
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String document;

    private String email;

    private String phone;

    private String deliveryAddress;

    private Boolean isActive = true;

}
