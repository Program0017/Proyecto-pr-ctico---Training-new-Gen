package com.globant.project.foodAplication.webApi.order;

import com.globant.project.foodAplication.commons.constants.endPoints.order.IOrderEndPoints;
import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.commons.dto.OrderDto;
import com.globant.project.foodAplication.model.order.OrderEntity;
import com.globant.project.foodAplication.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(IOrderEndPoints.ORDER_BASE_URL)
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderDto orderDto){
        OrderEntity order = OrderDto.fromDto(orderDto);
        return new ResponseEntity<OrderEntity>(orderService.createOrder(order), HttpStatus.CREATED );
    }

    @PatchMapping("/{uuid}/delivered/{localDateTime}")
    public ResponseEntity<OrderEntity> updateOrder(@PathVariable UUID uuid, @PathVariable LocalDateTime localDateTime) {
        LocalDate localDate = localDateTime.toLocalDate();
        return new ResponseEntity<OrderEntity>(orderService.updateOrder(uuid, localDate), HttpStatus.OK);
    }

}
