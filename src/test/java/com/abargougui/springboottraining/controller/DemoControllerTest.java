package com.abargougui.springboottraining.controller;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {
	@Autowired
	MockMvc mvc;

	@Test
	public void sayTheTime() throws Exception {
		String result1 = mvc.perform(get("/default")).andExpect(status().is2xxSuccessful()).andReturn().getResponse()
				.getContentAsString();

		String result2 = mvc.perform(get("/default")).andExpect(status().is2xxSuccessful()).andReturn().getResponse()
				.getContentAsString();
		assertNotEquals(result1, result2);
	}

	@Test
	public void sayTheTimeMany() throws Exception {
		mvc.perform(get("/many").param("name", "Adem").param("rep", "3")).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("Adem"))).andExpect(content().string(containsString("3.")))
				.andExpect(content().string(not(containsString("4."))));
	}

	@Test
	public void sayTheTimeManyParams() throws Exception {
		mvc.perform(get("/manyParams").param("name", "Adem").param("rep", "3")).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("Adem"))).andExpect(content().string(containsString("3.")))
				.andExpect(content().string(not(containsString("4."))));
	}

	@Test
	public void sayTheTimeManyParamsPath() throws Exception {
		mvc.perform(get("/many/{name}/{rep}", "John", "5").param("name", "Adem").param("rep", "3"))
				.andExpect(status().is2xxSuccessful()).andExpect(content().string(containsString("Adem")))
				.andExpect(content().string(containsString("3.")))
				.andExpect(content().string(not(containsString("4."))));
	}

	@Test
	public void sayTheTimeManyParamsPathFromPath() throws Exception {
		mvc.perform(get("/many/{name}/{rep}", "John", "5")).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("John"))).andExpect(content().string(containsString("5.")))
				.andExpect(content().string(not(containsString("6."))));
	}

	@Test
	public void sayTheTimeManyPathExplicit() throws Exception {
	}

	@Autowired
	ObjectMapper mapper;

	@Test
	public void sayTheTimeManyParamsPost() throws Exception {
		mvc.perform(post("/manyParams").param("name", "Adem").param("rep", "3")
				.content(mapper.writeValueAsString(new DemoController.Params("John", 5)))
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("John"))).andExpect(content().string(containsString("5.")))
				.andExpect(content().string(not(containsString("6."))));
	}

}