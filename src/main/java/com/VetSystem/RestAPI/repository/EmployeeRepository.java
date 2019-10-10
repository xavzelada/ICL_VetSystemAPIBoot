package com.VetSystem.RestAPI.repository;

import com.VetSystem.RestAPI.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    List<Employee> findBybranchid(Integer id);
    Employee findByName(String name);
}
