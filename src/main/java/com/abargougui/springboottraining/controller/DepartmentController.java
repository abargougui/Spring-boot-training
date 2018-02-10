package com.abargougui.springboottraining.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abargougui.springboottraining.hateoas.DepartmentResourceAssembler;
import com.abargougui.springboottraining.model.Department;
import com.abargougui.springboottraining.service.DepartmentService;

@RestController
@RequestMapping("department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@Autowired
	DepartmentResourceAssembler departmentResourceAssembler;

	@GetMapping("{id}")
	public Resource<Department> getDepartment(@PathVariable UUID id) {
		return departmentResourceAssembler.toResource(departmentService.find(id));
	}

	@PostMapping("{id}")
	public Resource<Department> updateDepartment(@PathVariable UUID id, @RequestBody Department department) {
		return departmentResourceAssembler.toResource(departmentService.update(department.withUuid(id)));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable UUID id) {
		try {
			departmentService.delete(id);
			return ResponseEntity.status(HttpStatus.GONE).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
	}

}
