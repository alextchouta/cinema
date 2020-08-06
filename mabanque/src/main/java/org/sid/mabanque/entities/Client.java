package org.sid.mabanque.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class Client implements Serializable {



    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String nom;
    private String email;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Compte> comptes;

    public Client(String nom, String email) {
        super();
        this.nom = nom;
        this.email = email;
    }

}
