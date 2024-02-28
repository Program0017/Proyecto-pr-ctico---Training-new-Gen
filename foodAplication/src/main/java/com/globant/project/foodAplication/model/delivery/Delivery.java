package com.globant.project.foodAplication.model.delivery;

import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.model.client.Client;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private  UUID UUID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    private Integer quantity;

    @Column(name = "extra_information")
    private String extraInformation;
    @Column(name = "creation_datetime")
    private Date creationDateTime;
    @Column(name = "delivery_date")
    private Date deleveryDate;
    @Column(name = "tax")
    private double tax;
    @Column(name = "grand_total")
    private double grandTotal;
    @Column(name = "sub_total")
    private double subTotal;

}
