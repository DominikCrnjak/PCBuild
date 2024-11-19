package org.example.pcbuilderproject.domainRepository;

import org.example.pcbuilderproject.domain.PC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCRepository extends JpaRepository<PC,Long> {
}
