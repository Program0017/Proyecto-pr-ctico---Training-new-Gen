package com.globant.project.foodAplication.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globant.project.foodAplication.model.product.ProductEntity;
import com.globant.project.foodAplication.model.client.ClientEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deliveries")
public class OrderEntity {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientDocument;
    private UUID productUuid;
    private int quantity;
    private String extraInformation;
    private LocalDate creationDateTime;
    private double subtotal;
    private double tax;
    private double grandTotal;
    private boolean delivered;
    private LocalDate deliveredDate;
    @Column(unique = true,
            columnDefinition = "BINARY(16)")
    private UUID uuid = UUID.randomUUID();

    public OrderEntity(Boolean isDelivered) {
        this.delivered = false;
    }
}
