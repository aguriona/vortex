package com.vortex.challenge.services;

import com.vortex.challenge.entities.Department;
import com.vortex.challenge.entities.Employee;
import com.vortex.challenge.entities.Location;

import java.util.List;

public interface IDepartmentServ {

    public List<Employee> create (Department department);
}
