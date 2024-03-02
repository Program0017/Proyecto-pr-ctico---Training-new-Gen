package com.globant.project.foodAplication.Seeds;

import com.globant.project.foodAplication.model.client.Client;
import com.globant.project.foodAplication.repository.client.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class Seeders implements ApplicationRunner {

    @Autowired
    private IClientRepository clientRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        for (int i = 0; i < 10; i++) {
            Client client = new Client();

            client.setId(1);
            client.setName("Juanito");
            client.setPhone("313339228");
            client.setDocument("2005899019");
            client.setEmail("correo@Correo.com");
            client.setDeliveryAddress("Alli arrbita con 32");
            client.setIsActive(true);

            clientRepository.save(client);
        }
    }
}
