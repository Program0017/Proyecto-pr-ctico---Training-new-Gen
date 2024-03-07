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
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String phone;

    @Column(unique = true)
    private String deliveryAddress;

    private Boolean isActive = true;

}
