package com.globant.project.foodAplication.model.client;
import java.util.UUID;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String email;

    private String phone;

    private String deliveryAddress;

    @Column(unique = true,
            columnDefinition = "BINARY(16)")
    private UUID uuid;

    public Client() {
        this.uuid = UUID.randomUUID();
    }

}
