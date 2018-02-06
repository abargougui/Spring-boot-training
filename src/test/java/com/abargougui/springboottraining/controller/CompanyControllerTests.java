package com.abargougui.springboottraining.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void getCompany() throws UnsupportedEncodingException, Exception {
		String result = mvc.perform(get("/")).andDo(print())
				.andExpect(content().contentTypeCompatibleWith("application/hal+json;charset=UTF-8")).andReturn()
				.getResponse().getContentAsString();

		Resource<String> value = mapper.readValue(result, new TypeReference<Resource<String>>() {
		});

		assertTrue(value.hasLink("self"));
		assertEquals(value.getLink("self"), value.getId());
		assertTrue(value.hasLink("departments"));

		assertTrue(value.hasLink("departments2"));
		assertEquals(value.getLink("departments2"), value.getLink("departments2"));
	}

}
