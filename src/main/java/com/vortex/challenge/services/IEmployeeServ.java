package com.vortex.challenge.services;

import com.vortex.challenge.dtos.CreateEmployeeDTO;
import com.vortex.challenge.dtos.ShowEmployeeDTO;
import com.vortex.challenge.entities.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IEmployeeServ {

    Employee create(CreateEmployeeDTO employee);

    List<Employee> findAllEmployees();

    Page<Employee> findAllPaginated(int page, int size);

    List<Employee> findAllPaginatedAndFiltered(int page, int size,
                                               Optional<Long> jobId,
                                               Optional<Long> managerId,
                                               Optional<String> lastname);

    ShowEmployeeDTO findById(Long id);

    void update(Long id, Employee employee);

    Optional<Employee> findByEmail(String email);

    void deleteById(Long id);

}
