package org.sid.microservicesmulticonnecteur.web;

import lombok.AllArgsConstructor;
import org.sid.microservicesmulticonnecteur.Model.Etudiant;
import org.sid.microservicesmulticonnecteur.repository.EtudiantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 * Creer une  Api RestController base sur Spring MVC
 *
 * et il existe aussi de creer une API avec Spring data rest
 * */
@AllArgsConstructor
//@RestController
public class EtudiantRestControllerBaseSurMVC {

    private EtudiantRepository etudiantRepository;

    @GetMapping("/etudiants")
    public List<Etudiant> listEtudiant() {
        return etudiantRepository.findAll();
    }


    @GetMapping("/etudiants/{id}")
    public Etudiant findEtudiantById(@PathVariable(name = "id") Long id) {
        return etudiantRepository.findById(id).get();
    }

    @PostMapping("/etudiants")
    public Etudiant saveEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }


    @PutMapping("/etudiants/{id}")
    public Etudiant updateEtudiant(@PathVariable(name = "id") Long id, @RequestBody Etudiant etudiant) {
        etudiant.setId(id);
        return etudiantRepository.save(etudiant);
    }


    @DeleteMapping("/etudiants/{id}")
    public void deleteEtudiant(@PathVariable(name = "id") Long id) {
        etudiantRepository.deleteById(id);
    }
}
