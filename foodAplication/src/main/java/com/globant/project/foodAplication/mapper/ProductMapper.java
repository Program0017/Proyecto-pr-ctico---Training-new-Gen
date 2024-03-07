package com.globant.project.foodAplication.mapper;

import com.globant.project.foodAplication.commons.dto.ProductDtoRequest;
import com.globant.project.foodAplication.commons.dto.ProductDto;
import com.globant.project.foodAplication.model.product.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMapper {

    public ProductEntity mapDtoToEntity(ProductDto productDto){
        return ProductEntity.builder()
                .fantasyName(productDto.getFantasyName())
                .description(productDto.getDescription())
                .category(productDto.getCategory())
                .price(productDto.getPrice())
                .available(productDto.getAvailable())
                .uuid(UUID.randomUUID())
                .stock(productDto.getStock())
                .build();
    }

    public ProductDto mapEntityToDto(ProductEntity productEntity){
        ProductDto productDto = new ProductDto();
        productDto.setFantasyName(productEntity.getFantasyName());
        productDto.setCategory(productEntity.getCategory());
        productDto.setPrice(productEntity.getPrice());
        productDto.setDescription(productEntity.getDescription());
        productDto.setAvailable(productEntity.getAvailable());
        productDto.setUuid(productEntity.getUuid());
        productDto.setStock(productEntity.getStock());

        return productDto;
    }
}
