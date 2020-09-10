package model;


import java.io.Serializable;

public class Etudiant implements Serializable {

    private Long id;
    private String nom;
    private String prenom;

    private Formation formation;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }




}
