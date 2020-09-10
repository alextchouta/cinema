package model;

import java.io.Serializable;
import java.util.Collection;


public class Formation implements Serializable {

    private Long id;
    private String nom;
    private int duree;


    private Collection<Etudiant> etudiants;

}
