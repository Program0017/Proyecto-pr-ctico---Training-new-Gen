package com.globant.project.foodAplication.webApi.product;

import com.globant.project.foodAplication.commons.constants.endPoints.product.IProductEndPoints;
import com.globant.project.foodAplication.model.product.ProductEntity;
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
    public ResponseEntity<ProductEntity> createProduct (@RequestBody ProductEntity productEntity){
        ProductEntity newProductEntity = productService.createProduct(productEntity);
        return new ResponseEntity<>(newProductEntity, HttpStatus.CREATED);
    }

    @GetMapping(IProductEndPoints.PRODUCT_GET_URL)
    public ResponseEntity<ProductEntity> productById (@PathVariable("uuid") UUID uuid){
        return new ResponseEntity<ProductEntity>(productService.findByUUID(uuid), HttpStatus.OK);
    }

    @PutMapping(IProductEndPoints.PRODUCT_UPDATE_URL)
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable("uuid") UUID uuid , @RequestBody ProductEntity productEntity){
        ProductEntity updatedProductEntity = productService.updateProduct(uuid, productEntity);
        return new ResponseEntity<>(updatedProductEntity, HttpStatus.OK);
    }

    @PatchMapping(IProductEndPoints.PRODUCT_DESACTIVATE_URL)
    public ResponseEntity<ProductEntity> deleteProduct(@PathVariable("uuid") UUID uuid ){
        return new ResponseEntity<ProductEntity>(productService.desactivateProduct(uuid), HttpStatus.OK);
    }


}
