package com.globant.project.foodAplication.testOrder;

import com.globant.project.foodAplication.model.client.Client;
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
    public void testCreateOrder() {
        Product product = new Product();
        product.setId(1);
        product.setPrice(10.0);

        Client client = new Client();
        client.setId(1);
        client.setName("jhon");
        client.setDocument("1110452096");
        client.setEmail("jhoncastro2004eg@gmail.com");
        client.setPhone("3005131873");
        client.setDeliveryAddress("calle 129 sur");
        client.setIsActive(true);

        Order order = new Order();
        order.setProduct(product);
        order.setQuantity(5);
        order.setExtraInformation("Extra info");
        order.setClient(client);

        when(iProductRepository.findById(1)).thenReturn(Optional.of(product));

        when(iOrderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

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
