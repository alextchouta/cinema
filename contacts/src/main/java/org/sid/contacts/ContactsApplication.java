package org.sid.contacts;

import lombok.AllArgsConstructor;
import org.sid.contacts.entities.Contact;
import org.sid.contacts.repository.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@AllArgsConstructor
public class ContactsApplication implements CommandLineRunner {

    private ContactRepository contactRepository;
    private RepositoryRestConfiguration restConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(ContactsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Contact.class);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        contactRepository.save(new Contact(null,"Hassani", "Mohamed", df.parse("12/01/1998"),"hassana@gmail.com", (long) 5523456, "hassani.jpg" ));
        contactRepository.save(new Contact(null,"Kaze", "Raissa", df.parse("12/01/1998"),"raissa@gmail.com",1L, "kaze.jpg" ));
        contactRepository.save(new Contact(null,"Ngwe", "Astride", df.parse("12/01/1998"),"astride@gmail.com",1L, "astride.jpg" ));

        contactRepository.findAll().forEach(c ->{
            System.out.println(c.getNom());
        });
    }
}
