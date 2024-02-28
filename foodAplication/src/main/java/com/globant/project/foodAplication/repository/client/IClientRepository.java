package com.globant.project.foodAplication.repository.client;

import com.globant.project.foodAplication.model.client.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends CrudRepository<Client, Long> {

}
