package com.VetSystem.RestAPI.repository;

import com.VetSystem.RestAPI.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{
    Company findByName(String name);
}
