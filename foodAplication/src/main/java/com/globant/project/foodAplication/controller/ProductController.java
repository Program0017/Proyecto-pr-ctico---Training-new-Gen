package com.globant.project.foodAplication.controller;

import com.globant.project.foodAplication.model.Product;
import com.globant.project.foodAplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<Product> productById (@PathVariable("productId") Long productId){
        return new ResponseEntity<Product>(productService.findById(productId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct (@RequestBody Product product){
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId , @RequestBody Product product){
        Product updatedProduct = productService.updateProduct(productId, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productId ){
        return new ResponseEntity<Product>(productService.deleteProduct(productId), HttpStatus.OK);
    }


}
