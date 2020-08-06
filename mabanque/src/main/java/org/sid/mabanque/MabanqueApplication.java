package org.sid.mabanque;

import org.sid.mabanque.entities.*;
import org.sid.mabanque.repositories.ClientRepository;
import org.sid.mabanque.repositories.CompteRepository;
import org.sid.mabanque.repositories.OperationRepository;
import org.sid.mabanque.services.IBanqueService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MabanqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(MabanqueApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository, CompteRepository compteRepository, OperationRepository operationRepository, IBanqueService banqueService){
        return args -> {
       Client c1 =  clientRepository.save(new Client("Hassan", "hassan@gmail.com"));
       Client c2 =  clientRepository.save(new Client("Rachid", "rachid@gmail.com"));

            Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 90000, c1, 6000));
            Compte cp2 = compteRepository.save(new CompteEpargne("c2", new Date(), 6000, c2, 5.5));

            Operation op1 = operationRepository.save(new Versement(new Date(), 9000, cp1));
            Operation op2 = operationRepository.save(new Versement(new Date(), 6000, cp1));
            Operation op3 = operationRepository.save(new Versement(new Date(), 2300, cp1));
            Operation op4 = operationRepository.save(new Retrait(new Date(), 9000, cp1));

            Operation cp2_op1 = operationRepository.save(new Versement(new Date(), 2300, cp2));
            Operation cp2_op2 = operationRepository.save(new Versement(new Date(), 400, cp2));
            Operation cp2_op3 = operationRepository.save(new Versement(new Date(), 2300, cp2));
            Operation cp2_op4 = operationRepository.save(new Retrait(new Date(), 3000, cp2));

            banqueService.verser("c1",11111);
        };
    }

}
