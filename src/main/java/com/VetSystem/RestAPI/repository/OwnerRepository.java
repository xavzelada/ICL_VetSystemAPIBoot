package com.VetSystem.RestAPI.repository;

import com.VetSystem.RestAPI.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer>{
    Owner findByName(String name);
}
