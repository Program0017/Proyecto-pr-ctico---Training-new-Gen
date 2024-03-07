package com.globant.project.foodAplication.Seeds;

import com.globant.project.foodAplication.model.client.ClientEntity;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class Seeders implements ApplicationRunner {
    private IClientRepository clientRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 0; i < 10; i++) {
            ClientEntity clientEntity = new ClientEntity();

            clientEntity.setId(1);
            clientEntity.setName("Juanito");
            clientEntity.setPhone("313339228");
            clientEntity.setDocument("2005899019");
            clientEntity.setEmail("correo@Correo.com");
            clientEntity.setDeliveryAddress("Alli arrbita con 32");
            clientEntity.setIsActive(true);

            clientRepository.save(clientEntity);
        }
    }
}
