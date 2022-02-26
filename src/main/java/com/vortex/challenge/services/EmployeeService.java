package com.vortex.challenge.services;

import com.vortex.challenge.dtos.ShowEmployeeDTO;
import com.vortex.challenge.entities.Employee;
import com.vortex.challenge.exceptions.EmployeeServiceException;
import com.vortex.challenge.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService implements IEmployeeServ{

    @Autowired
    private EmployeeRepository employeeRepository;

    //Insert method using DTO pattern
    @Override
    public Employee create(com.vortex.challenge.dtos.CreateEmployeeDTO employeeDTO) {

        Employee employee = new Employee();
        try {

            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPhone(employeeDTO.getPhone());
            employee.setSalary(employeeDTO.getSalary());
            employee.setHireDate(employeeDTO.getHireDate());
            employee.setJobId(employeeDTO.getJobId());
            employee.setCommission(employeeDTO.getCommission());
            employee.setManager(employeeDTO.getManager());
            employee.setDepartment(employeeDTO.getDepartment());

             employeeRepository.save(employee);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database Error");
        }

        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public ShowEmployeeDTO findById(Long id) throws EmployeeServiceException {

        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){throw new EmployeeServiceException("User not found");}

        ShowEmployeeDTO employeeDTO = new ShowEmployeeDTO();
        employeeDTO.setFirstName(employee.get().getFirstName());
        employeeDTO.setLastName(employee.get().getLastName());
        employeeDTO.setEmail(employee.get().getEmail());
        employeeDTO.setPhone(employee.get().getPhone());
        employeeDTO.setHireDate(employee.get().getHireDate());
        employeeDTO.setSalary(employee.get().getSalary());
        employeeDTO.setCommission(employee.get().getCommission());


        return employeeDTO;
    }

    @Override
    public void update(Long id, Employee employee) {

    }

    @Override
    public Optional<Employee> findByEmail(String email) throws EmployeeServiceException {

        Optional<Employee> employee = employeeRepository.findByEmail(email);

        if(employee.isPresent()){
            return employee;
        } else throw new EmployeeServiceException("User not found");

    }
}
