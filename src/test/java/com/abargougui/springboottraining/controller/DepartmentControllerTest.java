package com.abargougui.springboottraining.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.abargougui.springboottraining.model.Department;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerTest {

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void getDepartment() throws Exception {
		Department input = new Department("Sale");
		String location = addDepartment(input);

		Resource<Department> output = getDepartment(location);

		assertNotNull(output.getContent().getName());
		assertEquals(input.getName(), output.getContent().getName());

	}

	@Test
	public void updateDepartment() throws JsonProcessingException, Exception {

		Department input = new Department("Sale");
		String location = addDepartment(input);

		Resource<Department> output = getDepartment(location);

		String result = mvc
				.perform(post(output.getId().getHref()).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(output.getContent().withName("Billing"))))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		output = mapper.readValue(result, new TypeReference<Resource<Department>>() {
		});

		assertNotNull(output.getContent().getName());
		assertEquals("Billing", output.getContent().getName());

	}

	@Test
	public void updateDepartmentFailWrongId() throws JsonProcessingException, Exception {

		Department input = new Department("Sale");

		mvc.perform(post("/department/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(input))).andExpect(status().isNotFound());
	}

	@Test
	public void deleteADepartment() throws Exception {
		Department input = new Department("Billing");
		String location = addDepartment(input);

		mvc.perform(delete(location)).andExpect(status().isGone());

		mvc.perform(get(location)).andExpect(status().isNotFound());
	}

	@Test
	public void deleteADepartmentTwiceYieldsNotFound() throws Exception {
		Department input = new Department("Sale");
		String location = addDepartment(input);

		mvc.perform(delete(location)).andExpect(status().isGone());
		mvc.perform(delete(location)).andExpect(status().isNotModified());
	}

	private String addDepartment(Department department) throws Exception, JsonProcessingException {
		String location = mvc
				.perform(post("/departments").contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(department)))
				.andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
		return location;
	}

	private Resource<Department> getDepartment(String location)
			throws UnsupportedEncodingException, Exception, IOException, JsonParseException, JsonMappingException {
		String result = mvc.perform(get(location)).andDo(print()).andReturn().getResponse().getContentAsString();

		Resource<Department> output = mapper.readValue(result, new TypeReference<Resource<Department>>() {
		});
		return output;
	}

}
