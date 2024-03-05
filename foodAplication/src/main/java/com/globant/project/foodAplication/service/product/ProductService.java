package com.globant.project.foodAplication.service.product;

import com.globant.project.foodAplication.commons.dto.ProductDtoRequest;
import com.globant.project.foodAplication.commons.dto.ProductDtoResponse;
import com.globant.project.foodAplication.mapper.ProductMapper;
import com.globant.project.foodAplication.model.product.ProductEntity;
import com.globant.project.foodAplication.repository.product.IProductRepository;
import com.globant.project.foodAplication.utils.product.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductDtoResponse createProduct(ProductDtoRequest productDto){
        Optional<ProductEntity> existProduct = productRepository.findByFantasyName(productDto.getFantasyName().toUpperCase());
        ProductValidation.productPresentValidation(existProduct, productDto.getFantasyName());
        ProductEntity productEntity = productMapper.mapDtoToEntity(productDto);
        ProductValidation.productTotalValidation(productEntity);

        String upperCaseFantasyName = productDto.getFantasyName().toUpperCase();
        productEntity.setFantasyName(upperCaseFantasyName);
        return productMapper.mapEntityToDto(productRepository.save(productEntity)); }

    public ProductDtoResponse findByUUID(UUID uuidProduct){
        Optional<ProductEntity> product = productRepository.findByUuid(uuidProduct);
        ProductValidation.productEmptyValidation(product);
        ProductValidation.productFormatUuid(uuidProduct);

        return productMapper.mapEntityToDto(product.get());
    }

    public ProductDtoResponse updateProduct(UUID uuid, ProductDtoRequest productDto){
        Optional<ProductEntity> existingProduct = productRepository.findByUuid(uuid);
        ProductValidation.productEmptyValidation(existingProduct);
        ProductEntity productEntity = productMapper.mapDtoToEntity(productDto);
        ProductValidation.productEqualValidation(existingProduct, productEntity);

        Optional<ProductEntity> otherProduct = productRepository.findByFantasyName(productDto.getFantasyName());
        ProductValidation.productFantasyNameValidation(otherProduct);

        otherProduct = productRepository.findByFantasyName(productDto.getFantasyName());
        ProductValidation.productFantasyNameUUIDValidation(otherProduct, uuid);

        ProductValidation.productTotalValidation(productEntity);

        existingProduct.get().setFantasyName(productDto.getFantasyName());
        existingProduct.get().setCategory(productDto.getCategory());
        existingProduct.get().setAvailable(productDto.getAvailable());
        existingProduct.get().setDescription(productDto.getDescription());
        existingProduct.get().setPrice(productDto.getPrice());

        return productMapper.mapEntityToDto(productRepository.save(existingProduct.get()));
    }
    //pensar en metodo de resta de stock
    public ProductDtoResponse desactivateProduct(UUID uuid){
        Optional<ProductEntity> result = this.productRepository.findByUuid(uuid);
        if(result.isPresent()){
            result.get().setAvailable((!result.get().getAvailable()));
            return productMapper.mapEntityToDto(this.productRepository.save(result.get()));
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product with id %d does not exist", uuid));
        }
    }
}
