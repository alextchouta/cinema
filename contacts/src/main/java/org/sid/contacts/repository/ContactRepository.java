package org.sid.contacts.repository;

import org.sid.contacts.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@RepositoryRestResource
@CrossOrigin(origins = "*")
public interface ContactRepository extends JpaRepository<Contact,Long> {

    @Query("select c from Contact c where c.nom like :x")
    public Page<Contact> chercher(@Param("x") String mc, Pageable pageable);

    @RestResource(path = "/chercherContacts")
    public Page<Contact> findByNomContains(@Param("mc") String mc, Pageable pageable);
}
