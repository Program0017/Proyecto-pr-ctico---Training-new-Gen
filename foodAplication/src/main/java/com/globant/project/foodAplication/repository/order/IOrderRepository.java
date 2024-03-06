package com.globant.project.foodAplication.repository.order;

import com.globant.project.foodAplication.model.order.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IOrderRepository extends CrudRepository<OrderEntity, Integer> {
    Optional<OrderEntity> findByUuid(UUID uuid);
}
