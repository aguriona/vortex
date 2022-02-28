package com.vortex.challenge.services;

import com.vortex.challenge.entities.Department;
import com.vortex.challenge.entities.Employee;
import com.vortex.challenge.entities.Location;
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
    public List<Employee> create(Department department) throws NotFoundException {
        Logger logger = LoggerFactory.getLogger(DepartmentService.class);
        List<Employee> managerList = new ArrayList<>();
        List<Employee> employeeList = new ArrayList<>();
        List<Double> salaryList = new ArrayList<>();
        LocalDate diaActual = LocalDate.now();

        List<Department> departmentsByLocationId = departmentRepository.findAll().stream()
                .filter(dept -> dept.getLocation().getId().equals(department.getLocation().getId()))
                .collect(Collectors.toList());
        if (departmentsByLocationId.isEmpty()) throw new NotFoundException("Location NOT FOUND");

        for (Department manager : departmentsByLocationId) {
            managerList.add(manager.getManagerId());
        }

        for (Employee manager : managerList) {
            employeeList.addAll(employeeRepository.findByManager(manager.getId()));
        }

        for (Employee em : employeeList){
            salaryList.add(em.getSalary());
        }

        Double totalSalary = 0D;
        for (Double aDouble : salaryList) {

            totalSalary += aDouble;
        }
        double prom = totalSalary/salaryList.size();

        logger.info("Department By Location ID GET Employee Salary Average ---->>> " + prom +" Hoy= " + diaActual);

//        Si dicho promedio es mayor a 1000 y la fecha actual determina que
//        nos encontramos del 1 al 14 (primeras 2 semanas del mes), en ese
//        caso denega la inserción del Department. Si es menor a 1000 la
//                permite

        if (prom > 1000 && diaActual.getDayOfMonth() < 15){
            departmentRepository.save(department);
        }




        return employeeList;
    }
}
