package com.globant.project.foodAplication.webApi.product;

import com.globant.project.foodAplication.commons.constants.endPoints.product.IProductEndPoints;
import com.globant.project.foodAplication.commons.dto.ProductDtoRequest;
import com.globant.project.foodAplication.commons.dto.ProductDto;
import com.globant.project.foodAplication.mapper.ProductMapper;
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
    @Autowired
    private ProductMapper productMapper;

    @PostMapping(IProductEndPoints.PRODUCT_CREATE_URL)
    public ResponseEntity<ProductDto> createProduct (@RequestBody ProductDto productDto){
        ProductDto newProductEntity = productService.createProduct(productDto);
        return new ResponseEntity<>(newProductEntity, HttpStatus.CREATED);
    }

    @GetMapping(IProductEndPoints.PRODUCT_GET_URL)
    public ResponseEntity<ProductDto> productById (@PathVariable("uuid") UUID uuid){
        ProductDto productDto = productService.findByUUID(uuid);
        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }
    @PutMapping(IProductEndPoints.PRODUCT_UPDATE_URL)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("uuid") UUID uuid , @RequestBody ProductDto productDto){
        ProductDto productDto1 = productService.updateProduct(uuid, productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.NO_CONTENT);
    }

    @PatchMapping(IProductEndPoints.PRODUCT_DESACTIVATE_URL)
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("uuid") UUID uuid ){
        ProductDto productDto = productService.desactivateProduct(uuid);
        return new ResponseEntity<ProductDto>(productDto, HttpStatus.NO_CONTENT);
    }


}
