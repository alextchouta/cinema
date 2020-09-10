package org.sid.microservicesmulticonnecteur.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// for Soap webservice
@XmlAccessorType(XmlAccessType.FIELD)
public class Formation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int duree;

    @OneToMany(mappedBy = "formation")
    @JsonIgnore
    // for Soap webservice
    @XmlTransient
    private Collection<Etudiant> etudiants;
}
