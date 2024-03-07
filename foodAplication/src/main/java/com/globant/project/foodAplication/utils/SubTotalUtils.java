package com.globant.project.foodAplication.utils;

import org.springframework.stereotype.Component;

@Component
public class SubTotalUtils {
    public static Double makeSubTotal(Double price, Integer quantity){
        return price * quantity;
    }
}
