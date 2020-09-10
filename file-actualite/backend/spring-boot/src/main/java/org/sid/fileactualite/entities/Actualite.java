package org.sid.fileactualite.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Actualite implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String nomAuteur;
    private Date dateActualite;
    private String photo;

    @Lob
    // @Lob c est pour specifier au Framework de choisir le plus long type du type de cette categorie ()
    private String contenu;
}
