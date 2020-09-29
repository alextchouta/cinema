package org.sid.importexcelindatabase.repository;

import org.sid.importexcelindatabase.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
