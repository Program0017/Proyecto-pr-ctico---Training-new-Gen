package com.globant.project.foodAplication.model.delivery;

import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.model.client.Client;
import jakarta.persistence.*;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    private Integer quantity;

    @Column(name = "extra_information")
    private String extraInformation;
}
