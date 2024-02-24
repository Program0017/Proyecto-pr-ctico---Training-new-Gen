package com.globant.project.foodAplication.webApi.product;

import com.globant.project.foodAplication.commons.constants.endPoints.product.IProductEndPoints;
import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(IProductEndPoints.PRODUCT_BASE_URL)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(IProductEndPoints.PRODUCT_GET_URL)
    public ResponseEntity<Product> productById (@PathVariable("productId") Long productId){
        return new ResponseEntity<Product>(productService.findById(productId), HttpStatus.OK);
    }

    @PostMapping(IProductEndPoints.PRODUCT_CREATE_URL)
    public ResponseEntity<Product> createProduct (@RequestBody Product product){
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping(IProductEndPoints.PRODUCT_UPDATE_URL)
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId , @RequestBody Product product){
        Product updatedProduct = productService.updateProduct(productId, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping(IProductEndPoints.PRODUCT_DELETE_URL)
    public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productId ){
        return new ResponseEntity<Product>(productService.deleteProduct(productId), HttpStatus.OK);
    }


}
