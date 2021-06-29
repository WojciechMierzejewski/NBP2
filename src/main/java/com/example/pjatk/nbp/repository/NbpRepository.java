package com.example.pjatk.nbp.repository;

import com.example.pjatk.nbp.model.NbpResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NbpRepository extends JpaRepository<NbpResource, Long> {
}
