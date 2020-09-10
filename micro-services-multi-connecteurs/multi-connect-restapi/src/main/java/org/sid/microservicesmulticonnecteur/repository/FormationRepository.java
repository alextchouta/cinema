package org.sid.microservicesmulticonnecteur.repository;

import org.sid.microservicesmulticonnecteur.Model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FormationRepository extends JpaRepository<Formation, Long> {
}
