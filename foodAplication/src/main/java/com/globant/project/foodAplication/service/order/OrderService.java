package com.globant.project.foodAplication.service.order;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
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


    public Order createOrder(Order order) {
        try{
            Optional<Product> productOptional = this.productRepository.findById(order.getProduct().getId());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                Double price = product.getPrice();
                Integer quantity = order.getQuantity();
                order.setClient(order.getClient());
                order.setProduct(product);
                order.setQuantity(quantity);
                order.setExtraInformation(order.getExtraInformation());

                order.setUuid(UUID.fromString(UUID.randomUUID().toString()));

                Double subTotal = SubTotalUtils.makeSubTotal(price, quantity);
                order.setSubTotal(subTotal);

                Double tax = TaxUtils.makeTax(subTotal);
                order.setTax(tax);

                Double grandTotal = GrandTotalUtils.makeGranTotal(subTotal, tax);
                order.setGrandTotal(grandTotal);

                return this.iOrderRepository.save(order);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(IResponse.NOT_FOUND));
            }
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
