package com.globant.project.foodAplication.commons.dto;

import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.model.order.OrderEntity;
import com.globant.project.foodAplication.model.product.ProductEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String clientDocument;
    private UUID productUuid;
    private int quantity;
    private String extraInformation;

    public static OrderEntity fromDto(OrderDto dto) {
        OrderEntity order = new OrderEntity();
        order.setClientDocument(dto.getClientDocument());
        order.setProductUuid(dto.getProductUuid());
        order.setQuantity(dto.getQuantity());
        order.setExtraInformation(dto.getExtraInformation());
        order.setCreationDateTime(LocalDate.now()); // Assuming creationDateTime should be set to current date
        // other fields like subtotal, tax, grandTotal, delivered, deliveredDate are not included as they might need more information
        return order;
    }
}
