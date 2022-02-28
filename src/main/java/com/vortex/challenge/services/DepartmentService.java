package com.vortex.challenge.services;

import com.vortex.challenge.entities.Department;
import com.vortex.challenge.entities.Employee;
import com.vortex.challenge.exceptions.DepartmentServiceException;
import com.vortex.challenge.exceptions.NotFoundException;
import com.vortex.challenge.repositories.DepartmentRepository;
import com.vortex.challenge.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements IDepartmentServ {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Department create(Department department) throws NotFoundException, DepartmentServiceException {
        Logger logger = LoggerFactory.getLogger(DepartmentService.class);

        List<Employee> managerList = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();
        List<Double> salaryList = new ArrayList<>();

        LocalDate diaActual = LocalDate.now();
        logger.info("Actual Day ---->>> " + diaActual);

        //Find all departments in location parameter
        List<Department> departmentsByLocationId = departmentRepository.findAll().stream()
                .filter(dept -> dept.getLocation().getId().equals(department.getLocation().getId()))
                .collect(Collectors.toList());
        if (departmentsByLocationId.isEmpty()) throw new NotFoundException("Location NOT FOUND");

        //Find all manager in departments
        for (Department manager : departmentsByLocationId) {
            managerList.add(manager.getManagerId());
        }

        //Find all employees with related managers
        for (Employee manager : managerList) {
            employeeList.addAll(employeeRepository.findByManager(manager.getId()));
        }

        //Find all employees with related managers
        for (Employee em : employeeList) {
            salaryList.add(em.getSalary());
        }

        Double totalSalary = 0D;
        for (Double aDouble : salaryList) {
            totalSalary += aDouble;
        }
        double prom = totalSalary / salaryList.size();
        logger.info("Employees Salary Average ---->>> " + prom);

        if ((prom > 1000 && diaActual.getDayOfMonth() < 15) || (prom > 1500 && diaActual.getDayOfMonth() > 15)) {
            throw new DepartmentServiceException("Department can't be saved");
        }

        return departmentRepository.save(department);
    }
}
