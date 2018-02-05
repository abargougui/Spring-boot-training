package com.abargougui.springboottraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abargougui.springboottraining.model.Department;
import com.abargougui.springboottraining.service.DepartmentService;

@RestController
@RequestMapping("departments")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<Void> addADepartment(@RequestBody Department department) {
		departmentService.addDepartment(department);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
