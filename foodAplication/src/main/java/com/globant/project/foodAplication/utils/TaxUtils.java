package com.globant.project.foodAplication.utils;

import org.springframework.stereotype.Component;

@Component
public class TaxUtils {
    public static Double makeTax(Double subTotal){
            return subTotal * 0.19;
    }
}
