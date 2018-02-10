package com.abargougui.springboottraining.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Department {

	private final String name;
	private final UUID uuid;

	public String getName() {
		return name;
	}

	public Department(String name) {
		super();
		this.name = name;
		this.uuid = null;
	}

	@JsonCreator
	public Department(@JsonProperty("name") String name, @JsonProperty("uuid") UUID uuid) {
		super();
		this.name = name;
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
	}

	public Department withUuid(UUID uuid) {
		return new Department(name, uuid);
	}

	public Department withName(String name) {
		return new Department(name, uuid);
	}
}
