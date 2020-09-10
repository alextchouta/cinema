package org.sid.microservicesmulticonnecteur.web;

import lombok.AllArgsConstructor;
import org.sid.microservicesmulticonnecteur.Model.Etudiant;
import org.sid.microservicesmulticonnecteur.repository.EtudiantRepository;
import org.springframework.stereotype.Component;

import java.util.List;


// GET localhost:8585/service?wsdl
// wsdl permet de decrire le webservice, c est la description du webservice
@Component
@AllArgsConstructor
//@WebService
public class EtudiantSoapService {
    private EtudiantRepository etudiantRepository;

    //@WebMethod(operationName="listEtudiants")
    public List<Etudiant> listEtudiants() {
        return etudiantRepository.findAll();
    }

    //@WebMethod(operationName="listEtudiants")
    // findEtudiantById(@WebParam(name="id"))
    public Etudiant findEtudiantById(Long id) {
        return etudiantRepository.findById(id).get();
    }

    //@WebMethod(operationName="listEtudiants")
    // saveEtudiant(@WebParam(name="etudiant"))
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    //@WebMethod(operationName="listEtudiants")
    public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
        etudiant.setId(id);
        return etudiantRepository.save(etudiant);
    }

    //@WebMethod(operationName="listEtudiants")
    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

}
