package com.VetSystem.RestAPI.repository;

import com.VetSystem.RestAPI.entity.Pet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer>{
    List<Pet> findByownerid(Integer id);
    Pet findByName(String name);
}
