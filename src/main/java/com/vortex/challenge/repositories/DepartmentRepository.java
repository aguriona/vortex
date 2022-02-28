package com.vortex.challenge.repositories;

import com.vortex.challenge.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public List<Department> findByLocationId (Long locationId);
}
