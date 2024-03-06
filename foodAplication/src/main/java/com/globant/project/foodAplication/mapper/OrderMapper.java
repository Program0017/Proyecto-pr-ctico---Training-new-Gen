package com.globant.project.foodAplication.mapper;

import com.globant.project.foodAplication.commons.dto.OrderDto;
import com.globant.project.foodAplication.model.order.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderEntity mapDtoToEntity(OrderDto orderDto){
        return OrderEntity.builder()
                .product(orderDto.getProduct())
                .client(orderDto.getClient())
                .quantity(orderDto.getQuantity())
                .quantity(orderDto.getQuantity())
                .creationDateTime(orderDto.getCreationDateTime())
                .deliveryDate(orderDto.getDeliveryDate())
                .tax(orderDto.getTax())
                .grandTotal(orderDto.getGrandTotal())
                .subTotal(orderDto.getSubTotal())
                .isDelivered(orderDto.getIsDelivered())
                .uuid(orderDto.getUuid())
                .extraInformation(orderDto.getExtraInformation())
                .build();


    }

    public OrderDto mapEntitytoDto(OrderEntity orderEntity){
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
    }



}
