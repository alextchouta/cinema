package org.sid.mabanque.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {

    private double taux;

    public CompteEpargne(String codeCompte, Date dateCreation, double solde, Client client, double taux) {
        super(codeCompte, dateCreation, solde, client);
        this.taux = taux;
    }
}
