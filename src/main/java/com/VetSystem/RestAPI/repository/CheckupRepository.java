package com.VetSystem.RestAPI.repository;

import com.VetSystem.RestAPI.entity.Checkup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckupRepository extends JpaRepository<Checkup, Integer>{
    List<Checkup> findByvetid(Integer id);
    
}
