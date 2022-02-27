package com.vortex.challenge.controllers;

import com.vortex.challenge.dtos.ShowEmployeeDTO;
import com.vortex.challenge.entities.Employee;
import com.vortex.challenge.exceptions.DataAlreadyExistException;
import com.vortex.challenge.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> showEmployeeById(@PathVariable Long id) {
        ShowEmployeeDTO employeeDTO = employeeService.findById(id);

        return ResponseEntity.ok().body(employeeDTO);
    }

    @GetMapping
    public ResponseEntity<?> listEmployee(@RequestParam(defaultValue = "0") @Min(value = 0, message = "Page must be 0 or greater.") int page,
                                          @RequestParam(defaultValue = "10") @Min(value = 1, message = "Page size cannot be less than one.") int size,
                                          @RequestParam(value = "jobId", required = false) Optional<Long> jobId,
                                          @RequestParam(value = "managerId", required = false) Optional<Long> managerId,
                                          @RequestParam(value = "lastName", required = false) Optional<String> lastName) {
        List<Employee> employeeList = employeeService.findAll(page, size, jobId, managerId, lastName);

        return ResponseEntity.ok().body(employeeList);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody com.vortex.challenge.dtos.CreateEmployeeDTO employeeDTO) throws DataAlreadyExistException {
        Optional<Employee> employeeOptional = employeeService.findByEmail(employeeDTO.getEmail());

        if (employeeOptional.isPresent()) {
            throw new DataAlreadyExistException("Employee already exist");
        }
        ;

        return ResponseEntity.created(URI.create(""))
                .body(employeeService.create(employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
