package com.globant.project.foodAplication.webApi.product;

import com.globant.project.foodAplication.commons.constants.endPoints.product.IProductEndPoints;
import com.globant.project.foodAplication.commons.dto.ClientDto;
import com.globant.project.foodAplication.commons.dto.ProductDtoRequest;
import com.globant.project.foodAplication.commons.dto.ProductDto;
import com.globant.project.foodAplication.mapper.ProductMapper;
import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.model.product.ProductEntity;
import com.globant.project.foodAplication.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    //-------------------------------------------------------------------//

    @Operation(summary = "Uptade products", description = "Update products with UUID")
    @ApiResponse(responseCode = "201", description = "Product Updated")
    @PutMapping(IProductEndPoints.PRODUCT_UPDATE_URL)
    public ResponseEntity<ProductDto> updateProduct(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product to be updated", required = true, content = @Content(schema = @Schema(implementation = ProductEntity.class)))  @RequestBody ProductDto productDto, ProductDto uuid){
        ProductDto productDto1 = productService.updateProduct(uuid.getUuid(), productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.NO_CONTENT);
    }

    //------------------------------------------------------------------//
    @Operation(summary = "Inactivate Product", description = "Inactivate product, change avaliable to false")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductEntity.class)))
    @PatchMapping(IProductEndPoints.PRODUCT_DESACTIVATE_URL)
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("uuid") UUID uuid ){
        ProductDto productDto = productService.desactivateProduct(uuid);
        return new ResponseEntity<ProductDto>(productDto, HttpStatus.NO_CONTENT);
    }


}
