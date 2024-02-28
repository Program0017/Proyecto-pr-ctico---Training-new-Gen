package com.globant.project.foodAplication.service.delivery;

import com.globant.project.foodAplication.commons.dto.DeliveryDto;
import com.globant.project.foodAplication.model.client.Client;
import com.globant.project.foodAplication.model.delivery.Delivery;
import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import com.globant.project.foodAplication.repository.delivery.IDeliveryRepository;
import com.globant.project.foodAplication.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service

public class DeliveryService {
    @Autowired
    private IDeliveryRepository iDeliveryRepository;

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IClientRepository clientRepository;

    public Delivery createDelivery(DeliveryDto deliveryDto) {
        Delivery delivery = new Delivery();
        delivery.setClient(deliveryDto.getClient_id());
        delivery.setProduct(deliveryDto.getProduct_id());
        delivery.setQuantity(deliveryDto.getQuantity());
        delivery.setExtraInformation(deliveryDto.getExtraInformation());
        iDeliveryRepository.save(delivery);
        return delivery;
    }
}
