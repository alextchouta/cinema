package org.sid.microservicesmulticonnecteur;

import org.sid.microservicesmulticonnecteur.Model.Etudiant;
import org.sid.microservicesmulticonnecteur.Model.Formation;
import org.sid.microservicesmulticonnecteur.repository.EtudiantRepository;
import org.sid.microservicesmulticonnecteur.repository.FormationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class MicroServicesMultiConnecteurApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServicesMultiConnecteurApplication.class, args);
    }

    @Bean
    CommandLineRunner start(EtudiantRepository etudiantRepository, FormationRepository formationRepository) {
        return args -> {

            Stream.of("JAVA", "JEE", "Oracle").forEach(nom -> {
                formationRepository.save(new Formation(null, nom, 30, null));
            });
            Etudiant etudiant1 = new Etudiant();
            etudiant1.setNom("Ibrahim");
            etudiant1.setPrenom("Hassan");
            etudiant1.setFormation(new Formation(1L, null, 0, null));
            etudiantRepository.save(etudiant1);

            Etudiant etudiant2 = new Etudiant();
            etudiant2.setNom("Yaakoubi");
            etudiant2.setPrenom("Yassine");
            etudiant2.setFormation(new Formation(1L, null, 0, null));
            etudiantRepository.save(etudiant2);

            Etudiant etudiant3 = new Etudiant();
            etudiant3.setNom("Ibrahim");
            etudiant3.setPrenom("Samira");
            etudiant3.setFormation(new Formation(2L, null, 0, null));
            etudiantRepository.save(etudiant3);

            Etudiant etudiant4 = new Etudiant();
            etudiant4.setNom("Ibrahim");
            etudiant4.setPrenom("Rachida");
            etudiant4.setFormation(new Formation(3L, null, 0, null));
            etudiantRepository.save(etudiant4);

            formationRepository.findAll().forEach(f -> {
                System.out.println(f.getNom());
            });
        };
    }

}
