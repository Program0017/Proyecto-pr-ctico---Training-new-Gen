package com.globant.project.foodAplication.webApi.order;

import com.globant.project.foodAplication.commons.constants.endPoints.order.IOrderEndPoints;
import com.globant.project.foodAplication.commons.dto.OrderDto;
import com.globant.project.foodAplication.model.order.Order;
import com.globant.project.foodAplication.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = IOrderEndPoints.ORDER_BASE_URL)
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(IOrderEndPoints.ORDER_CREATE_URL)
    public ResponseEntity<Order> orderCreate(@RequestBody OrderDto orderDto){
        Order order = orderService.createOrder(orderDto);
        return new ResponseEntity<> (order, HttpStatus.CREATED);
    }




}