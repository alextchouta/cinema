import webserviceRmi.IEtudiantRemote;
import java.rmi.Naming;


public class ClientRMI {

    public static void main(String[] args) {

        IEtudiantRemote stub;

        {
            try {
                stub = (IEtudiantRemote) Naming.lookup("rmi://localhost:1099/ETUDIANT");
                stub.listEtudiants().forEach(etudiant -> {
                    System.out.println(etudiant.getNom() + " " + etudiant.getPrenom());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
