package com.abargougui.springboottraining.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.abargougui.springboottraining.model.Department;
import com.abargougui.springboottraining.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	DepartmentService departmentService;

	@Test
	public void addABookmark() throws Exception {
		mvc.perform(post("/departments").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(new Department("theUrl")))).andExpect(status().isCreated());
		Mockito.verify(departmentService).addDepartment(Mockito.any(Department.class));
	}

}
