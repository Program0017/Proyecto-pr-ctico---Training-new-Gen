package com.globant.project.foodAplication.service.order;

import com.globant.project.foodAplication.commons.dto.OrderDto;
import com.globant.project.foodAplication.model.order.Order;
import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import com.globant.project.foodAplication.repository.order.IOrderRepository;
import com.globant.project.foodAplication.repository.product.IProductRepository;
import com.globant.project.foodAplication.utils.GrandTotalUtils;
import com.globant.project.foodAplication.utils.SubTotalUtils;
import com.globant.project.foodAplication.utils.TaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service

public class OrderService {
    @Autowired
    private IOrderRepository iOrderRepository;

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IClientRepository clientRepository;

    public Order createOrder(OrderDto orderDto) {
        Product product = productRepository.findById(orderDto.getProduct_id().getUuid()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Double price = product.getPrice();
        Integer quantity = orderDto.getQuantity();

        Order order = new Order();
        order.setClient(orderDto.getClient_id());
        order.setProduct(orderDto.getProduct_id());
        order.setQuantity(orderDto.getQuantity());
        order.setExtraInformation(orderDto.getExtraInformation());

        Double subTotal = SubTotalUtils.makeSubTotal(price , quantity);
        order.setSubTotal(subTotal);

        Double tax = TaxUtils.makeTax(subTotal);
        order.setTax(tax);

        Double grandTotal = GrandTotalUtils.makeGranTotal(subTotal, tax);
        order.setGrandTotal(grandTotal);

;

        iOrderRepository.save(order);
        return order;


    }
}
