package org.sid.bankspringbatch.repository;

import org.sid.bankspringbatch.model.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransactionRepository extends JpaRepository<BankTransaction,Long> {
}
