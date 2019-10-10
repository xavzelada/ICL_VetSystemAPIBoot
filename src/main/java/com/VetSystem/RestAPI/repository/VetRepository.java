package com.VetSystem.RestAPI.repository;

import com.VetSystem.RestAPI.entity.Vet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer>{
    List<Vet> findByemployeeid(Integer id);
    Vet findByLicenseid(String licenceId);
}
