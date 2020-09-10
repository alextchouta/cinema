package org.sid.microservicesmulticonnecteur.repository;

import org.sid.microservicesmulticonnecteur.Model.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    // Ohne l annotation Restresource
    // GET localhost:8080/etudiants/search/findByNomContains?mc=L
    @RestResource(path = "/parNom")
    // GET localhost:8080/etudiants/search/parNom?mc=L
    public Page<Etudiant> findByNomContains(@Param("mc") String mc, Pageable pageable);
}
