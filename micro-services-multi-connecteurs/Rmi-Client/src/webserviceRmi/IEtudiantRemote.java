package webserviceRmi;

import model.Etudiant;
import model.Formation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


/*
 * l interface doit respecter les 2 conditions suivantes:
 *   - implementer l interface Remote
 *   - Toutes les methodes de l interface doivent lever l exception RemoteException
 * */
public interface IEtudiantRemote extends Remote {

    public List<Etudiant> listEtudiants() throws RemoteException;

    public Etudiant getEtudiant(Long id) throws RemoteException;

    public Formation saveFormation(Formation f) throws RemoteException;
}
