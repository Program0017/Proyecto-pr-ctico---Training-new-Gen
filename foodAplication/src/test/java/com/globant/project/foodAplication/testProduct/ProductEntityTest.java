package com.globant.project.foodAplication.testProduct;

import com.globant.project.foodAplication.model.product.ProductEntity;
import com.globant.project.foodAplication.repository.product.IProductRepository;
import com.globant.project.foodAplication.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductEntityTest {

    @Mock
    private IProductRepository iProductRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    public void testCreateProduct() {

        ProductEntity productEntityToCreate = new ProductEntity();
        ProductEntity savedProductEntity = new ProductEntity();
        UUID uuid = UUID.randomUUID();
        savedProductEntity.setUuid(uuid);

        when(iProductRepository.save(productEntityToCreate)).thenReturn(savedProductEntity);

        ProductEntity createdProductEntity = productService.createProduct(productEntityToCreate);

        assertNotNull(createdProductEntity.getUuid());
        assertEquals(uuid, createdProductEntity.getUuid());
        verify(iProductRepository, times(1)).save(productEntityToCreate);
    }

    @Test
    public void testUpdateProductExists() {

        UUID uuid = UUID.randomUUID();
        ProductEntity existingProductEntity = new ProductEntity();
        ProductEntity updatedProductEntity = new ProductEntity();

        when(iProductRepository.findByUuid(uuid)).thenReturn(Optional.of(existingProductEntity));
        when(iProductRepository.save(existingProductEntity)).thenReturn(existingProductEntity);

        ProductEntity result = productService.updateProduct(uuid, updatedProductEntity);

        assertSame(existingProductEntity, result);
        assertEquals(updatedProductEntity.getFantasyName(), existingProductEntity.getFantasyName());
        assertEquals(updatedProductEntity.getDescription(), existingProductEntity.getDescription());
        verify(iProductRepository, times(1)).findByUuid(uuid);
        verify(iProductRepository, times(1)).save(existingProductEntity);
    }

    @Test
    public void testDesactivateProductExists() {

        UUID uuid = UUID.randomUUID();
        ProductEntity existingProductEntity = new ProductEntity();
        existingProductEntity.setAvailable(true);

        when(iProductRepository.findByUuid(uuid)).thenReturn(Optional.of(existingProductEntity));
        when(iProductRepository.save(existingProductEntity)).thenReturn(existingProductEntity);

        ProductEntity result = productService.desactivateProduct(uuid);

        assertFalse(result.getAvailable());
        verify(iProductRepository, times(1)).findByUuid(uuid);
        verify(iProductRepository, times(1)).save(existingProductEntity);
    }

}
