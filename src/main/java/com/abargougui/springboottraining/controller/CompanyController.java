package com.abargougui.springboottraining.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.BasicLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CompanyController {

	@GetMapping("/")
	public Resource<String> getServiceDocument() {
		return new Resource<String>("Company", BasicLinkBuilder.linkToCurrentMapping().withSelfRel(),
				BasicLinkBuilder.linkToCurrentMapping().slash("departments").withRel("departments"),
				linkTo(methodOn(DepartmentsController.class).addADepartment(null)).withRel("departments2"));
	}

}
