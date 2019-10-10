package com.VetSystem.RestAPI.repository;

import com.VetSystem.RestAPI.entity.Checkupreport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckupreportRepository extends JpaRepository<Checkupreport, Integer>{
    List<Checkupreport> findBycheckupid(Integer id);
    List<Checkupreport> findBypetid(Integer id);
}
