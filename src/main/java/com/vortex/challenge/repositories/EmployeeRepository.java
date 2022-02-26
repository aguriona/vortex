package com.vortex.challenge.repositories;

import com.vortex.challenge.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

      //  @Query(value = "SELECT e FROM Employee e WHERE e.eMail = ?1")
        public Optional<Employee> findByEmail(String email);
}
