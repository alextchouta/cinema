package org.sid.microservicesmulticonnecteur.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p2", types = Etudiant.class)
// GET http://localhost:8080/etudiants?projection=projection1
public interface EtudiantProjection2 {

    @Value("#{target.nom} #{target.prenom}")
    public String getFullName();
}
