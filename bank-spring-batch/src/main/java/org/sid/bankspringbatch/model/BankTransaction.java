package org.sid.bankspringbatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class BankTransaction {
    @Id
    private Long id;
    private Long accountID;
    private Date transactionDate;
    // ce champ n est pas persistant, il ne va pas etre gespeichert dans la base de donnees
    @Transient
    private String strTransactionDate;
    private String transactionType;
    private double amount;
}
