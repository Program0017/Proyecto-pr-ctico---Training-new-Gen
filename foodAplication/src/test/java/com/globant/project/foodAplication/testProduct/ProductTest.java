package com.globant.project.foodAplication.testProduct;

import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.repository.product.IProductRepository;
import com.globant.project.foodAplication.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @Mock
    private IProductRepository iProductRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    public void testCreateProduct() {

        Product productToCreate = new Product();
        Product savedProduct = new Product();
        UUID uuid = UUID.randomUUID();
        savedProduct.setUuid(uuid);

        when(iProductRepository.save(productToCreate)).thenReturn(savedProduct);

        Product createdProduct = productService.createProduct(productToCreate);

        assertNotNull(createdProduct.getUuid());
        assertEquals(uuid, createdProduct.getUuid());
        verify(iProductRepository, times(1)).save(productToCreate);
    }

    @Test
    public void testUpdateProductExists() {

        UUID uuid = UUID.randomUUID();
        Product existingProduct = new Product();
        Product updatedProduct = new Product();

        when(iProductRepository.findByUuid(uuid)).thenReturn(Optional.of(existingProduct));
        when(iProductRepository.save(existingProduct)).thenReturn(existingProduct);

        Product result = productService.updateProduct(uuid, updatedProduct);

        assertSame(existingProduct, result);
        assertEquals(updatedProduct.getFantasyName(), existingProduct.getFantasyName());
        assertEquals(updatedProduct.getDescription(), existingProduct.getDescription());
        verify(iProductRepository, times(1)).findByUuid(uuid);
        verify(iProductRepository, times(1)).save(existingProduct);
    }

    @Test
    public void testDesactivateProductExists() {

        UUID uuid = UUID.randomUUID();
        Product existingProduct = new Product();
        existingProduct.setAvailable(true);

        when(iProductRepository.findByUuid(uuid)).thenReturn(Optional.of(existingProduct));
        when(iProductRepository.save(existingProduct)).thenReturn(existingProduct);

        Product result = productService.desactivateProduct(uuid);

        assertFalse(result.getAvailable());
        verify(iProductRepository, times(1)).findByUuid(uuid);
        verify(iProductRepository, times(1)).save(existingProduct);
    }

}
