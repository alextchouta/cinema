package org.sid.fileactualite.repository;

import org.sid.fileactualite.entities.Actualite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface ActualiteRepository extends JpaRepository<Actualite,Long> {
    @Query("select a.titre, a.nomAuteur, a.dateActualite, a.photo from Actualite a")
    public Page<Actualite> listActualite(Pageable pageable);
}
