package com.globant.project.foodAplication.webApi.product;

import com.globant.project.foodAplication.commons.constants.endPoints.product.IProductEndPoints;
import com.globant.project.foodAplication.commons.dto.ProductDtoRequest;
import com.globant.project.foodAplication.commons.dto.ProductDtoResponse;
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
    public ResponseEntity<ProductDtoResponse> createProduct (@RequestBody ProductDtoRequest productDto){

        ProductEntity newProductEntity = productMapper.mapDtoToEntity(productDto);
        ProductDtoResponse productDto1 = productService.createProduct(productDto);

        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    @GetMapping(IProductEndPoints.PRODUCT_GET_URL)
    public ResponseEntity<ProductDtoResponse> productById (@PathVariable("uuid") UUID uuid){
        ProductDtoResponse productDto = productService.findByUUID(uuid);
        return new ResponseEntity<ProductDtoResponse>(productDto, HttpStatus.OK);
    }

    @PutMapping(IProductEndPoints.PRODUCT_UPDATE_URL)
    public ResponseEntity<ProductDtoResponse> updateProduct(@PathVariable("uuid") UUID uuid , @RequestBody ProductDtoRequest productDto){
        ProductDtoResponse productDto1 = productService.updateProduct(uuid, productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }

    @PatchMapping(IProductEndPoints.PRODUCT_DESACTIVATE_URL)
    public ResponseEntity<ProductDtoResponse> deleteProduct(@PathVariable("uuid") UUID uuid ){
        ProductDtoResponse productDto = productService.desactivateProduct(uuid);
        return new ResponseEntity<ProductDtoResponse>(productDto, HttpStatus.OK);
    }


}
