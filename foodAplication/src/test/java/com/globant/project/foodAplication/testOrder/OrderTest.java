package com.globant.project.foodAplication.testOrder;

import com.globant.project.foodAplication.model.order.Order;
import com.globant.project.foodAplication.model.product.Product;
import com.globant.project.foodAplication.repository.order.IOrderRepository;
import com.globant.project.foodAplication.repository.product.IProductRepository;
import com.globant.project.foodAplication.service.order.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderTest {

    @Mock
    private IOrderRepository iOrderRepository;

    @Mock
    private IProductRepository iProductRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCreateOrderWhenProductExists() {
        Order orderToCreate = new Order();
        Product product = new Product();
        product.setId(1);
        orderToCreate.setProduct(product);
        Integer quantity = 5;
        Double price = 10.0;
        Double subTotal = price * quantity;
        Double tax = subTotal * 0.1;
        Double grandTotal = subTotal + tax;

        when(iProductRepository.findById(product.getId())).thenReturn(Optional.of(product));

        Order result = orderService.createOrder(orderToCreate);

        assertNotNull(result.getUuid());
        assertEquals(orderToCreate.getClient(), result.getClient());
        assertEquals(orderToCreate.getQuantity(), result.getQuantity());
        assertEquals(orderToCreate.getExtraInformation(), result.getExtraInformation());
        assertEquals(subTotal, result.getSubTotal());
        assertEquals(tax, result.getTax());
        assertEquals(grandTotal, result.getGrandTotal());
        verify(iOrderRepository, times(1)).save(orderToCreate);
    }


}
