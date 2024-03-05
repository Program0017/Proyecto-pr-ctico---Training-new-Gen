package com.globant.project.foodAplication.service.order;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.model.order.Order;
import com.globant.project.foodAplication.model.product.ProductEntity;
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
            Optional<ProductEntity> productOptional = this.productRepository.findById(order.getProductEntity().getId());
            if (productOptional.isPresent()) {
                ProductEntity productEntity = productOptional.get();
                Double price = productEntity.getPrice();
                Integer quantity = order.getQuantity();
                order.setClientEntity(order.getClientEntity());
                order.setProductEntity(productEntity);
                order.setQuantity(quantity);
                order.setExtraInformation(order.getExtraInformation());
                order.setCreationDateTime(LocalDateTime.now());

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
