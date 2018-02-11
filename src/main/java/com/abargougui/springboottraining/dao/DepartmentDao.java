package com.abargougui.springboottraining.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends CrudRepository<com.abargougui.springboottraining.model.Department, UUID> {

}
