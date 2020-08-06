package org.sid.mabanque.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

    private double decouvert;

    public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client, double decouvert) {
        super(codeCompte, dateCreation, solde, client);
        this.decouvert = decouvert;
    }


}
