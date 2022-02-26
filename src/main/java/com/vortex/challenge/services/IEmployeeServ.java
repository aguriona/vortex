package com.vortex.challenge.services;

import com.vortex.challenge.dtos.ShowEmployeeDTO;
import com.vortex.challenge.entities.Employee;
import com.vortex.challenge.exceptions.DataAlreadyExistException;

import java.util.List;
import java.util.Optional;

public interface IEmployeeServ {

    public Employee create (com.vortex.challenge.dtos.CreateEmployeeDTO employee) throws DataAlreadyExistException;

    public List<Employee> findAll ();

    public ShowEmployeeDTO findById (Long id);

    public void update (Long id, Employee employee);

    public Optional<Employee> findByEmail (String email);

}
