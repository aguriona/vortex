package com.vortex.challenge.controllers;

import com.vortex.challenge.dtos.ShowEmployeeDTO;
import com.vortex.challenge.entities.Employee;
import com.vortex.challenge.exceptions.DataAlreadyExistException;
import com.vortex.challenge.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.net.URI;
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
                                          @RequestParam(defaultValue = "30") @Min(value = 1, message = "Page size cannot be less than one.") int size,
                                          @RequestParam(value = "jobId", required = false) Long jobId,
                                          @RequestParam(value = "managerId", required = false) Long managerId,
                                          @RequestParam(value = "lastName", required = false) String lastName) {

        Page<Employee> employeeList = employeeService.findAllPaginated(page, size);

        if (jobId != null && jobId > 0) {
            employeeList = new PageImpl<>(employeeService.jobIdFilter(employeeList, jobId),
                    employeeList.getPageable(), employeeList.getTotalElements());
        }
        if (managerId != null && managerId > 0) {
            employeeList = new PageImpl<>(employeeService.managerIdFilter(employeeList, managerId),
                    employeeList.getPageable(), employeeList.getTotalElements());
        }
        if (lastName != null && lastName.length() > 0) {
            employeeList = new PageImpl<>(employeeService.lastNameFilter(employeeList, lastName),
                    employeeList.getPageable(), employeeList.getTotalElements());
        }
        return ResponseEntity.ok().body(employeeList);
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody com.vortex.challenge.dtos.CreateEmployeeDTO employeeDTO) throws DataAlreadyExistException {
        Optional<Employee> employeeOptional = employeeService.findByEmail(employeeDTO.getEmail());

        if (employeeOptional.isPresent()) {
            throw new DataAlreadyExistException("Employee already exist");
        } else return ResponseEntity.created(URI.create(""))
                .body(employeeService.create(employeeDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
        return "EMPLOYEE DELETED";
    }

}
