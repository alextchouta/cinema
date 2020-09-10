package org.sid.microservicesmulticonnecteur.web;

import lombok.AllArgsConstructor;
import org.sid.microservicesmulticonnecteur.Model.Etudiant;
import org.sid.microservicesmulticonnecteur.repository.EtudiantRepository;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/*
* Creer une  Api Rest base sur JAXRS et deployer avec Jersey
*
* for it to work u havem to activate the bean in the class MyConfig
* */
@AllArgsConstructor
@Path("/scolarite")
@Component
public class EtudiantRestControllerWithJAXRS {

    private EtudiantRepository etudiantRepository;

    @GET
    @Path("/etudiants")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Etudiant> listEtudiant()
    {
            return etudiantRepository.findAll();
    }

    @GET
    @Path("/etudiants/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Etudiant findEtudiantById(@PathParam(value = "id") Long id)
    {
        return etudiantRepository.findById(id).get();
    }

    @POST
    @Path("/etudiants")
    @Produces(MediaType.APPLICATION_JSON)
    public Etudiant saveEtudiant(Etudiant etudiant)
    {
        return etudiantRepository.save(etudiant);
    }

    @PUT
    @Path("/etudiants/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Etudiant updateEtudiant(@PathParam(value = "id") Long id, Etudiant etudiant)
    {
        etudiant.setId(id);
        return etudiantRepository.save(etudiant);
    }

    @DELETE
    @Path("/etudiants/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEtudiant(@PathParam(value = "id") Long id)
    {

        etudiantRepository.deleteById(id);
    }
}
