package com.abargougui.springboottraining.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.abargougui.springboottraining.model.Department;

@Service
public class DepartmentService {

	Map<UUID, Department> db = new HashMap<UUID, Department>();

	public UUID addDepartment(Department department) {
		UUID uuid = UUID.randomUUID();
		db.put(uuid, department.withUuid(uuid));
		return uuid;
	}

	public Department find(UUID id) {
		if (db.containsKey(id))
			return db.get(id);
		throw new NoSuchElementException();
	}

	public Collection<Department> findAll() {
		return db.values();
	}

	public Department update(Department department) {
		if (!db.containsKey(department.getUuid()))
			throw new NoSuchElementException();
		db.put(department.getUuid(), department);
		return department;
	}

	public void delete(UUID id) {
		if (!db.containsKey(id))
			throw new NoSuchElementException();
		db.remove(id);
	}

}
