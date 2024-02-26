package com.globant.project.foodAplication.repository.delivery;

import com.globant.project.foodAplication.model.client.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeliveryRepository extends CrudRepository<Client, Long> {
}
