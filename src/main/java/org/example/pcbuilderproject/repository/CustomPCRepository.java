package org.example.pcbuilderproject.repository;

import org.example.pcbuilderproject.domain.CustomPC;
import org.example.pcbuilderproject.domain.PC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomPCRepository extends JpaRepository<CustomPC,Long> {
}
