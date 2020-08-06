package org.sid.mabanque.entities;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("V")
@NoArgsConstructor
public class Versement extends Operation {

    public Versement(Date dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte);
    }
}
