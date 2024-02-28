package com.globant.project.foodAplication.webApi.delivery;

import com.globant.project.foodAplication.commons.constants.endPoints.delivery.IDeliveryEndPoints;
import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.commons.dto.DeliveryDto;
import com.globant.project.foodAplication.model.client.Client;
import com.globant.project.foodAplication.model.delivery.Delivery;
import com.globant.project.foodAplication.service.delivery.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = IDeliveryEndPoints.DELIVERY_BASE_URL)
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @PostMapping(IDeliveryEndPoints.DELIVERY_CREATE_URL)
    public ResponseEntity<Delivery> deliverycreate(@RequestBody DeliveryDto deliveryDto){
        Delivery deliveryDto1 = deliveryService.createDelivery(deliveryDto);
        return new ResponseEntity<> (deliveryDto1, HttpStatus.CREATED);
    }




}
