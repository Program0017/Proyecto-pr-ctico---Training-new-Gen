package com.globant.project.foodAplication.testOrder;

import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.model.order.OrderEntity;
import com.globant.project.foodAplication.model.product.ProductEntity;
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
    public void testCreateOrder() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1);
        productEntity.setPrice(10.0);

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1);
        clientEntity.setName("jhon");
        clientEntity.setDocument("1110452096");
        clientEntity.setEmail("jhoncastro2004eg@gmail.com");
        clientEntity.setPhone("3005131873");
        clientEntity.setDeliveryAddress("calle 129 sur");
        clientEntity.setIsActive(true);

        OrderEntity order = new OrderEntity();
        order.setProduct(productEntity);
        order.setQuantity(5);
        order.setExtraInformation("Extra info");
        order.setClient(clientEntity);

        when(iProductRepository.findById(1)).thenReturn(Optional.of(productEntity));

        when(iOrderRepository.save(order)).thenReturn(order);

        OrderEntity createdOrder = orderService.createOrder(order);

        assertEquals(order.getProduct(), createdOrder.getProduct());
        assertEquals(order.getQuantity(), createdOrder.getQuantity());
        assertEquals(order.getExtraInformation(), createdOrder.getExtraInformation());
        assertEquals(order.getClient(), createdOrder.getClient());
        assertEquals(order.getSubTotal(), createdOrder.getSubTotal());
        assertEquals(order.getTax(), createdOrder.getTax());
        assertEquals(order.getGrandTotal(), createdOrder.getGrandTotal());
        assertEquals(order.getUuid(), createdOrder.getUuid());
    }


}
