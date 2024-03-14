package com.globant.project.foodAplication.mapper;

import com.globant.project.foodAplication.commons.dto.OrderDto;
import com.globant.project.foodAplication.model.order.OrderEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderMapper {
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
   /*
    public OrderDto mapEntitytoDto(OrderEntity orderEntity) {

        OrderDto  orderDto = new OrderDto();
        orderDto.setProduct(orderEntity.getProduct());
        orderDto.setClient(orderEntity.getClient());
        orderDto.setQuantity(orderEntity.getQuantity());
        orderDto.setCreationDateTime(orderEntity.getCreationDateTime());
        orderDto.setDeliveryDate(orderEntity.getDeliveryDate());
        orderDto.setTax(orderEntity.getTax());
        orderDto.setGrandTotal(orderEntity.getGrandTotal());
        orderDto.setSubTotal(orderEntity.getSubTotal());
        orderDto.setIsDelivered(orderEntity.getIsDelivered());
        orderDto.setUuid(orderEntity.getUuid());
        orderDto.setExtraInformation(orderEntity.getExtraInformation());
        return orderDto;


    } */
}
