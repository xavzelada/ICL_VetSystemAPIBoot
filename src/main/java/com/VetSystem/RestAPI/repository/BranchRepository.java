package com.VetSystem.RestAPI.repository;

import com.VetSystem.RestAPI.entity.Branch;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer>{
    List<Branch> findBycompanyid(Integer id);
    Branch findByName(String name);
}
