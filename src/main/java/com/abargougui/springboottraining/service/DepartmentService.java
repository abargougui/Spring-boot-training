package com.abargougui.springboottraining.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abargougui.springboottraining.dao.DepartmentDao;
import com.abargougui.springboottraining.model.Department;

@Service
public class DepartmentService {

	@Autowired
	DepartmentDao dao;

	public UUID addDepartment(Department department) {
		return dao.save(department).getUuid();
	}

	public Department find(UUID id) {
		return Optional.ofNullable(dao.findOne(id)).orElseThrow(() -> new NoSuchElementException());
	}

	public Iterable<Department> findAll() {
		return dao.findAll();
	}

	public Department update(Department department) {
		find(department.getUuid());
		return dao.save(department);
	}

	public void delete(UUID id) {
		dao.findOne(id);
		dao.delete(id);
	}

}
