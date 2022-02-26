package com.vortex.challenge.controllers;

import com.vortex.challenge.dtos.ShowEmployeeDTO;
import com.vortex.challenge.entities.Employee;
import com.vortex.challenge.exceptions.DataAlreadyExistException;
import com.vortex.challenge.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> listEmployeeById (@PathVariable Long id){
        ShowEmployeeDTO employeeDTO = employeeService.findById(id);

        return ResponseEntity.ok().body(employeeDTO);

    }


    @PostMapping
    public ResponseEntity<?> register(@RequestBody com.vortex.challenge.dtos.CreateEmployeeDTO employeeDTO) throws DataAlreadyExistException {
        Optional<Employee> employeeOptional = employeeService.findByEmail(employeeDTO.getEmail());

        if (employeeOptional.isPresent()){
            throw new DataAlreadyExistException("Employee already exist");
        };

        return ResponseEntity.created(URI.create(""))
                .body(employeeService.create(employeeDTO));
    }


}
