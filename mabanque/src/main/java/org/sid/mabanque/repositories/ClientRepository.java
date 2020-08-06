package org.sid.mabanque.repositories;

import org.sid.mabanque.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface ClientRepository extends JpaRepository<Client,Long> {
}
