package com.globant.project.foodAplication.commons.constants.endPoints.order;

public interface IOrderEndPoints {

    String ORDER_BASE_URL = "/order";

    String ORDER_CREATE_URL = "/create";
    String CLIENT_UPDATE_URL = "/{orderId}";
    String ORDER_DELETE_URL = "/{orderId}";
    String ORDER_BASE_GET = "/{orderId}";


}
