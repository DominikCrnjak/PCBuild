package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domainComponents.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCCaseRepository extends JpaRepository<Case,Long> {
}