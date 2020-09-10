package org.sid.microservicesmulticonnecteur.Model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "projection1", types = Etudiant.class)
// GET http://localhost:8080/etudiants?projection=projection1
public interface EtudiantProjection {
    public Long getId();
    public String getNom();
    public Formation getFormation();
}
