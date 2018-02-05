package com.abargougui.springboottraining.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Department {

	private final String name;

	public String getName() {
		return name;
	}

	@JsonCreator
	public Department(@JsonProperty("name") String name) {
		super();
		this.name = name;
	}

}
