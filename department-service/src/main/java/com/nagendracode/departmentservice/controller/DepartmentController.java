package com.nagendracode.departmentservice.controller;

import com.nagendracode.departmentservice.client.EmployeeClient;
import com.nagendracode.departmentservice.model.Department;
import com.nagendracode.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department){
        LOGGER.info("add department:"+department);
      return  departmentRepository.addDepartment(department);
    }

    @GetMapping
     public List<Department> findAll() {
        LOGGER.info("find all departments");
        return departmentRepository.findAll();
     }


    @GetMapping("/{id}")
     public Department findById(@PathVariable Long id) {
        LOGGER.info("add department by {id}:"+id);
        return departmentRepository.findById(id);
     }

    @GetMapping("/with-employees")
    public List<Department> findemps() {
        LOGGER.info("Dept find");
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(department -> department.setEmployees(employeeClient.findByDepartment(department.getId())));
        return departments;
    }
}
