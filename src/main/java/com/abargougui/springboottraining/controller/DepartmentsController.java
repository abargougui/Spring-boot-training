package com.abargougui.springboottraining.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abargougui.springboottraining.model.Department;
import com.abargougui.springboottraining.service.DepartmentService;

@RestController
@RequestMapping("departments")
public class DepartmentsController {

	@Autowired
	DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<Void> addADepartment(@RequestBody Department department) {
		UUID uuid = departmentService.addDepartment(department);
		System.out.println("UUID : " + uuid);
		return ResponseEntity.created(BasicLinkBuilder.linkToCurrentMapping().slash("department").slash(uuid).toUri())
				.build();

	}

	@GetMapping
	public Resources<Department> getAllDepartments() {
		return new Resources<>(departmentService.findAll());
	}
}
