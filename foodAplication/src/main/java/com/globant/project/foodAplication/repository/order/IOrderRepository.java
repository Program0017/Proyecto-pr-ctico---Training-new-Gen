package com.globant.project.foodAplication.repository.order;

import com.globant.project.foodAplication.model.order.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends CrudRepository<OrderEntity, Integer> {
}
