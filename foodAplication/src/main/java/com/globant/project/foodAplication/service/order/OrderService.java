package com.globant.project.foodAplication.service.order;

import com.globant.project.foodAplication.commons.constants.response.IResponse;
import com.globant.project.foodAplication.commons.dto.OrderDto;
import com.globant.project.foodAplication.mapper.OrderMapper;
import com.globant.project.foodAplication.model.order.OrderEntity;
import com.globant.project.foodAplication.model.product.ProductEntity;
import com.globant.project.foodAplication.repository.order.IOrderRepository;
import com.globant.project.foodAplication.repository.product.IProductRepository;
import com.globant.project.foodAplication.utils.GrandTotalUtils;
import com.globant.project.foodAplication.utils.SubTotalUtils;
import com.globant.project.foodAplication.utils.TaxUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service

public class OrderService {

    @Autowired

    private IOrderRepository orderRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private SubTotalUtils subTotalUtils;
    @Autowired
    private GrandTotalUtils grandTotalUtils;
    @Autowired
    private TaxUtils taxUtils;
    @Autowired
    private OrderMapper orderMapper;



    public OrderDto createOrder(OrderDto orderDto) {
        try{
            Optional<ProductEntity> productOptional = this.productRepository.findById(orderDto.getProduct().getId());
            System.out.println("Clase: " +productOptional);
            if (productOptional.isPresent()) {
                ProductEntity productEntity = productOptional.get();
                Double price = productEntity.getPrice();
                Integer quantity = orderDto.getQuantity();
                orderDto.setClient(orderDto.getClient());
                orderDto.setProduct(productEntity);
                orderDto.setQuantity(quantity);
                orderDto.setExtraInformation(orderDto.getExtraInformation());
                orderDto.setCreationDateTime(LocalDateTime.now());

                orderDto.setUuid(UUID.fromString(UUID.randomUUID().toString()));

                Double subTotal = SubTotalUtils.makeSubTotal(price, quantity);
                orderDto.setSubTotal(subTotal);

                Double tax = TaxUtils.makeTax(subTotal);
                orderDto.setTax(tax);

                Double grandTotal = GrandTotalUtils.makeGranTotal(subTotal, tax);
                orderDto.setGrandTotal(grandTotal);

                OrderEntity order = orderMapper.mapDtoToEntity(orderDto);
                return orderMapper.mapEntitytoDto(orderRepository.save(order));
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(IResponse.NOT_FOUND));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public OrderDto orderFinish(UUID uuid, LocalDateTime deliveryDate) {
        Optional<OrderEntity> result = orderRepository.findByUuid(uuid);
        if (result.isPresent()){
            OrderEntity  orderEntity = result.get();
            orderEntity.setDeliveryDate(deliveryDate);
            orderEntity.setIsDelivered(true);
            return orderMapper.mapEntitytoDto(orderRepository.save(orderEntity));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(IResponse.NOT_FOUND));
        }
    }
}
