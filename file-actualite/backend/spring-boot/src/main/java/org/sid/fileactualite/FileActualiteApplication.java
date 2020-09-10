package org.sid.fileactualite;

import org.sid.fileactualite.entities.Actualite;
import org.sid.fileactualite.repository.ActualiteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class FileActualiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileActualiteApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ActualiteRepository actualiteRepository)
    {
        return args -> {
            for (int i = 0; i < 100; i++) {
                Actualite actualite = new Actualite(null, "Concours MAster SID","Admin",new Date(),"concours"+i+".png","ENSET lance le <strong> concours  </strong> pour l acces au master");
                actualiteRepository.save(actualite);
            }

        };
    }

}
