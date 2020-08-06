package org.sid.mabanque.entities;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue("R")
@NoArgsConstructor
public class Retrait extends Operation {
    public Retrait(Date dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte);
    }
}
