package com.globant.project.foodAplication.service.order;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.commons.dto.OrderDto;
import com.globant.project.foodAplication.mapper.OrderMapper;
import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.model.order.OrderEntity;
import com.globant.project.foodAplication.model.product.ProductEntity;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import com.globant.project.foodAplication.repository.order.IOrderRepository;
import com.globant.project.foodAplication.repository.product.IProductRepository;
import com.globant.project.foodAplication.utils.GrandTotalUtils;
import com.globant.project.foodAplication.utils.RestQuantityUtils;
import com.globant.project.foodAplication.utils.SubTotalUtils;
import com.globant.project.foodAplication.utils.TaxUtils;
import com.globant.project.foodAplication.utils.client.ClientValidation;
import com.globant.project.foodAplication.utils.product.ProductValidation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service

public class OrderService {

    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    IProductRepository productRepository;
    @Autowired
    private GrandTotalUtils grandTotalUtils;
    @Autowired
    TaxUtils taxUtils;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    IClientRepository clientRepository;


    public OrderEntity createOrder(OrderEntity order) {
        Optional<ClientEntity> client = clientRepository.findByDocument(order.getClientDocument());
        ClientValidation.clientEmptyValidation(client, order.getClientDocument());

        Optional<ProductEntity> product = productRepository.findByUuid(order.getProductUuid());
        ProductValidation.productEmptyValidation(product);

        order.setSubtotal(Double.parseDouble(String.valueOf(product.get().getPrice())) * order.getQuantity());
        order.setTax(order.getSubtotal() * 0.19);
        order.setGrandTotal(order.getTax() + order.getSubtotal());
        order.setDelivered(false);
        order.setCreationDateTime(LocalDate.now());
        return orderRepository.save(order);
    }


    public OrderEntity updateOrder(UUID uuid, LocalDate localDate) {

        ProductValidation.productFormatUuid(uuid);
        Optional<OrderEntity> order = orderRepository.findByUuid(uuid);
        if(order.isPresent()){
            order.get().setDelivered(true);
            order.get().setDeliveredDate(localDate);
            return this.orderRepository.save(order.get());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Order with uuid %d does not exist", uuid));
        }
    }
}
