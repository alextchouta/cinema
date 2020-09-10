package org.sid.microservicesmulticonnecteur.webserviceRmi;

import lombok.AllArgsConstructor;
import org.sid.microservicesmulticonnecteur.Model.Etudiant;
import org.sid.microservicesmulticonnecteur.Model.Formation;
import org.sid.microservicesmulticonnecteur.repository.EtudiantRepository;
import org.sid.microservicesmulticonnecteur.repository.FormationRepository;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;
import java.util.List;

@AllArgsConstructor
@Component
public class EtudiantRmiServiceImpl implements IEtudiantRemote {
    private EtudiantRepository etudiantRepository;
    private FormationRepository formationRepository;

    @Override
    public List<Etudiant> listEtudiants() throws RemoteException {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant getEtudiant(Long id) throws RemoteException {
        return etudiantRepository.findById(id).get();
    }

    @Override
    public Formation saveFormation(Formation f) throws RemoteException {
        return formationRepository.save(f);
    }
}
