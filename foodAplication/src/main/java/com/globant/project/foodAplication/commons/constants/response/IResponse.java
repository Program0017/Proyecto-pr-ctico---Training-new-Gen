package com.globant.project.foodAplication.commons.constants.response;

public interface IResponse {

    String OPERATION_SUCCESS = "Operation successful";
    String OPERATION_FAIL = "Operation failed";
    String CREATE_SUCCESS = "OK created successfully";
    String CREATE_FAIL = "Not created successfully";
    String UPDATE_SUCCESS = "OK updated successfully";
    String UPDATE_FAIL = "Not updated successfully";
    String DELETE_SUCCESS = "OK deleted successfully";
    String DELETE_FAIL = "Not deleted successfully";
    String NOT_FOUND = "Operation failed. Not found";
    String DOCUMENT_FAIL = "Could not convert document: ";
    String INTERNAL_SERVER_ERROR = "Internal Server error. Unexpected system error";
    String INTERNAL_SERVER = "Internal server error";
}
