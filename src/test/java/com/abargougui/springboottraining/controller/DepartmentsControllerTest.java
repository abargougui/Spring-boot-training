package com.abargougui.springboottraining.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.abargougui.springboottraining.model.Department;
import com.abargougui.springboottraining.service.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentsControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@SpyBean
	DepartmentService departmentService;

	@Test
	public void addADepartment() throws Exception {
		addDepartment(new Department("Sale"));
		Mockito.verify(departmentService).addDepartment(Mockito.any(Department.class));
	}

	@Test
	public void getAllDepartments() throws Exception {

		addDepartment(new Department("Sale"));
		addDepartment(new Department("Billing"));

		String result = mvc.perform(get("/departments")).andDo(print()).andReturn().getResponse().getContentAsString();
		Resources<Department> output = mapper.readValue(result, new TypeReference<Resources<Department>>() {
		});

		assertTrue(output.getContent().size() >= 2);
		assertTrue(output.getContent().stream().anyMatch(department -> department.getName().equals("Sale")));
		assertTrue(output.getContent().stream().anyMatch(department -> department.getName().equals("Billing")));
	}

	private void addDepartment(Department department) throws Exception, JsonProcessingException {
		mvc.perform(post("/departments").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(department))).andExpect(status().isCreated());
	}

}
