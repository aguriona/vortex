package com.vortex.challenge.controllers;


import com.vortex.challenge.entities.Department;
import com.vortex.challenge.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Department department){

        return ResponseEntity.created(URI.create("")).body(departmentService.create(department));
    }


}
