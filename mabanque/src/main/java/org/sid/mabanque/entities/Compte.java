package org.sid.mabanque.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
/*
 * pour l heritage , la strategie SINGLE_TABLE veut dire qu on utilise une table pour toute la herarchie de classe (une Table compte ou on va stocker tous les types de compte)
 *
 *TABLE_PER_CLASS = une table pour chaque classe
 *
 * */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYP_CPTE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Compte implements Serializable {
    @Id
    private String codeCompte;
    private Date dateCreation;
    private double solde;
    @ManyToOne
    @JoinColumn(name = "CODE_CLI")
    private Client client;
    @OneToMany(mappedBy = "compte")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Operation> operations;


    public Compte(String codeCompte, Date dateCreation, double solde, Client client) {
        this.codeCompte = codeCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
    }

}
