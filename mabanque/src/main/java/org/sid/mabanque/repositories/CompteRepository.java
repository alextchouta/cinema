package org.sid.mabanque.repositories;

import org.sid.mabanque.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface CompteRepository extends JpaRepository<Compte,String> {
}
