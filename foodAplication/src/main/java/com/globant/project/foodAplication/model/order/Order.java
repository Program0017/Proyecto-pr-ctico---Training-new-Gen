package com.globant.project.foodAplication.model.order;

import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.model.client.Client;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deliveries")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 1;

    @Column(unique = true)
    private  UUID uuid;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "extra_information")
    private String extraInformation;

    @Column(name = "creation_datetime")
    private LocalDateTime creationDateTime;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "grand_total")
    private Double grandTotal;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "delivered")
    private Boolean isDelivered = false;

    public Order(LocalDateTime creationDateTime, Boolean isDelivered) {
        this.creationDateTime = LocalDateTime.now();
        this.isDelivered = false;
    }
}
