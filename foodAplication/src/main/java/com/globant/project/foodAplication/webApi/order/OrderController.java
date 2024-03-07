package com.globant.project.foodAplication.webApi.order;

import com.globant.project.foodAplication.commons.constants.endPoints.order.IOrderEndPoints;
import com.globant.project.foodAplication.commons.dto.OrderDto;
import com.globant.project.foodAplication.model.order.OrderEntity;
import com.globant.project.foodAplication.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(IOrderEndPoints.ORDER_BASE_URL)
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping(IOrderEndPoints.ORDER_CREATE_URL)
    public ResponseEntity<OrderDto> orderCreate(@RequestBody OrderDto  orderDto){
        OrderDto newOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping(IOrderEndPoints.ORDER_UPDATE_URL)
    public  ResponseEntity<OrderDto> orderFinish(@PathVariable("uuid") UUID uuid, @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deliveryDate){
        OrderDto newOrder = orderService.orderFinish(uuid,deliveryDate);
        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }



}
