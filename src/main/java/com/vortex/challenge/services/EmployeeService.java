package com.vortex.challenge.services;

import com.vortex.challenge.dtos.ShowEmployeeDTO;
import com.vortex.challenge.entities.Employee;
import com.vortex.challenge.exceptions.EmployeeServiceException;
import com.vortex.challenge.exceptions.NotFoundException;
import com.vortex.challenge.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeService implements IEmployeeServ {

    @Autowired
    private EmployeeRepository employeeRepository;

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
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database Error");
        }

        return employee;
    }

    @Override
    public List<Employee> findAll(int page, int size,
                                  Optional<Long> jobId,
                                  Optional<Long> managerId,
                                  Optional<String> lastname) throws NotFoundException{
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC,"hireDate");
        if (pageRequest.isUnpaged()) throw new NotFoundException("Page not found");

        Page<Employee> employeePage = employeeRepository.findAll(pageRequest);

        if (jobId.isPresent()){
            return jobIdfilter(employeePage, jobId.get());
        }
        if (managerId.isPresent()){
            return managerIdfilter(employeePage, managerId.get());
        }
        if (lastname.isPresent()){
            return lastNamefilter(employeePage, lastname.get());
        }else
            return employeePage.toList();
            }

    @Override
    public ShowEmployeeDTO findById(Long id) throws EmployeeServiceException {

        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new EmployeeServiceException("User not found");
        }

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
    public void update(Long id, Employee employee) throws NotFoundException {
        Optional<Employee> updatedEmployee = employeeRepository.findById(id);
        if (updatedEmployee.isEmpty()) {
            throw new NotFoundException("Employee NOT found in DB");
        }

        updatedEmployee.get().setFirstName(employee.getFirstName());
        updatedEmployee.get().setLastName(employee.getLastName());
        updatedEmployee.get().setEmail(employee.getEmail());
        updatedEmployee.get().setPhone(employee.getPhone());
        updatedEmployee.get().setHireDate(employee.getHireDate());
        updatedEmployee.get().setSalary(employee.getSalary());
        updatedEmployee.get().setCommission(employee.getCommission());
        employeeRepository.save(updatedEmployee.get());
        System.out.println("Employee UPDATED");
    }

    @Override
    public Optional<Employee> findByEmail(String email) throws EmployeeServiceException {

        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if (employee.isPresent()) {
            return employee;
        } else throw new EmployeeServiceException("User not found");

    }

    @Override
    public void deleteById(Long id) {
        Optional<Employee> updatedEmployee = employeeRepository.findById(id);
        if (updatedEmployee.isEmpty()) {
            throw new NotFoundException("Employee NOT found in DB");
        }
        employeeRepository.deleteById(id);
        System.out.println("Employee DELETED");
    }

    public List<Employee> jobIdfilter (Page<Employee> employeePage, Long jobId) {
        return employeePage.stream()
                           .filter(employee -> employee.getJobId().getId().equals(jobId))
                           .collect(Collectors.toList());
    }
    public List<Employee> managerIdfilter (Page<Employee> employeePage, Long managerId) {
        return employeePage.stream()
                .filter(employee -> employee.getManager().equals(managerId))
                .collect(Collectors.toList());
    }
    public List<Employee> lastNamefilter (Page<Employee> employeePage, String lastName) {
        return employeePage.stream()
                .filter(employee -> employee.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }
}
