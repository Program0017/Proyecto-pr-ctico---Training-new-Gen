package com.globant.project.foodAplication.repository.client;

import com.globant.project.foodAplication.model.client.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClientRepository extends CrudRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findByDocument(String document);
    List<ClientEntity> findAllByOrderByDocumentAsc();
    List<ClientEntity> findAllByOrderByDocumentDesc();
    List<ClientEntity> findAllByOrderByNameAsc();
    List<ClientEntity> findAllByOrderByNameDesc();
    List<ClientEntity> findAllByOrderByDeliveryAddressAsc();
    List<ClientEntity> findAllByOrderByDeliveryAddressDesc();


}
