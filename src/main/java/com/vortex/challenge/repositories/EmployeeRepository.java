package com.vortex.challenge.repositories;

import com.vortex.challenge.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT e FROM Employee e WHERE e.email = ?1")
    Optional<Employee> findByEmail(String email);

    @Query(value = "SELECT e FROM Employee e WHERE e.manager.id = ?1")
    List<Employee> findByManager(Long id);

}
