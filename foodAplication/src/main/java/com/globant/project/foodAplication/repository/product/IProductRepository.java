package com.globant.project.foodAplication.repository.product;

import com.globant.project.foodAplication.model.product.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductRepository extends CrudRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByUuid(UUID uuid);
    Optional<ProductEntity> findByFantasyName(String fantasyName);

    Boolean existsByFantasyName(String fantasyName);





}
