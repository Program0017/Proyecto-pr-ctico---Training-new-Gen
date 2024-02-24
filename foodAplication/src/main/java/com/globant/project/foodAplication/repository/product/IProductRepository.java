package com.globant.project.foodAplication.repository.product;

import com.globant.project.foodAplication.model.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<Product, Long> {

}
