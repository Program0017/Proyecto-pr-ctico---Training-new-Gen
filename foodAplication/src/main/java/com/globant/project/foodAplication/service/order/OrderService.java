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

import java.util.Optional;
import java.util.UUID;

@Service

public class OrderService {
    @Autowired
    private IOrderRepository iOrderRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IClientRepository clientRepository;


    public Order createOrder(Order order) {
        Optional<Product> product = productRepository.findById(order.getProduct().getId());

        Double price = product.get().getPrice();
        Integer quantity = order.getQuantity();

        System.out.println(product.get().getId());

        order.setClient(order.getClient());
        order.setProduct(order.getProduct());
        order.setQuantity(order.getQuantity());
        order.setExtraInformation(order.getExtraInformation());

        order.setUuid(UUID.fromString(UUID.randomUUID().toString()));

        Double subTotal = SubTotalUtils.makeSubTotal(price , quantity);
        order.setSubTotal(subTotal);

        Double tax = TaxUtils.makeTax(subTotal);
        order.setTax(tax);

        Double grandTotal = GrandTotalUtils.makeGranTotal(subTotal, tax);
        order.setGrandTotal(grandTotal);

        System.out.println(order.getId());

        iOrderRepository.save(order);
        return order;


    }
}
