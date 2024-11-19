package org.example.pcbuilderproject.componentsRepository;

import org.example.pcbuilderproject.componentsDomain.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCCaseRepository extends JpaRepository<Case,Long> {
}
