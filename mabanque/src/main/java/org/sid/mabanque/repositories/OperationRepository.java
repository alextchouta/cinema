package org.sid.mabanque.repositories;

import org.sid.mabanque.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface OperationRepository extends JpaRepository<Operation,Long> {

    @Query("select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc")
    public Page<Operation> listOperations(@Param("x") String codeCpte, Pageable pageable);
}
