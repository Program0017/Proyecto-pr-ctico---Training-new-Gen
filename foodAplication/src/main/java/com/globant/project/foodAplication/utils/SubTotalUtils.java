package com.globant.project.foodAplication.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

public class SubTotalUtils {
    public static Double makeSubTotal(Double price, Integer quantity){
        return price * quantity;
    }
}
