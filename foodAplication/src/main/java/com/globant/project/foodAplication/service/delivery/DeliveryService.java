package com.globant.project.foodAplication.service.delivery;

import com.globant.project.foodAplication.model.client.Client;
import com.globant.project.foodAplication.model.delivery.Delivery;
import com.globant.project.foodAplication.repository.delivery.IDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class DeliveryService {
    @Autowired
    private IDeliveryRepository iDeliveryRepository;
}
