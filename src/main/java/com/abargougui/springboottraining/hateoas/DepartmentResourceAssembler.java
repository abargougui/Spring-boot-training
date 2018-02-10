package com.abargougui.springboottraining.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.abargougui.springboottraining.controller.DepartmentController;
import com.abargougui.springboottraining.model.Department;

@Component
public class DepartmentResourceAssembler implements ResourceAssembler<Department, Resource<Department>> {

	@Override
	public Resource<Department> toResource(Department entity) {
		return new Resource<>(entity,
				linkTo(methodOn(DepartmentController.class).getDepartment(entity.getUuid())).withSelfRel());
	}

}
