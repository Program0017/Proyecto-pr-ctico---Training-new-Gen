package com.globant.project.foodAplication.utils;


import org.springframework.stereotype.Component;

@Component
public class RestQuantityUtils {
    public static Integer restQuantity(Integer quantity, Integer stock){
        return stock-quantity;
    }
}
