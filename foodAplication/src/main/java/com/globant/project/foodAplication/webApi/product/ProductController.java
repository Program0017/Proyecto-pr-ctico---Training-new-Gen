package com.globant.project.foodAplication.webApi.product;

import com.globant.project.foodAplication.commons.constants.endPoints.product.IProductEndPoints;
import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(IProductEndPoints.PRODUCT_BASE_URL)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(IProductEndPoints.PRODUCT_CREATE_URL)
    public ResponseEntity<Product> createProduct (@RequestBody Product product){
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping(IProductEndPoints.PRODUCT_GET_URL)
    public ResponseEntity<Product> productById (@PathVariable("uuid") UUID uuid){
        return new ResponseEntity<Product>(productService.findByUUID(uuid), HttpStatus.OK);
    }

    @PutMapping(IProductEndPoints.PRODUCT_UPDATE_URL)
    public ResponseEntity<Product> updateProduct(@PathVariable("uuid") UUID uuid , @RequestBody Product product){
        Product updatedProduct = productService.updateProduct(uuid, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(IProductEndPoints.PRODUCT_DELETE_URL)
    public ResponseEntity<Product> deleteProduct(@PathVariable("uuid") UUID uuid ){
        return new ResponseEntity<Product>(productService.deleteProduct(uuid), HttpStatus.OK);
    }


}
