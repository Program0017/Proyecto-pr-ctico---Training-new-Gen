package com.globant.project.foodAplication.commons.constants.endPoints.delivery;

public interface IDeliveryEndPoints {

    String DELIVERY_BASE_URL = "/delivery";

    String DELIVERY_CREATE_URL = "/create";
    String CLIENT_UPDATE_URL = "/{deliveryId}";
    String DELIVERY_DELETE_URL = "/{deliveryId}";
    String DELIVERY_BASE_GET = "/{deliveryId}";


}
