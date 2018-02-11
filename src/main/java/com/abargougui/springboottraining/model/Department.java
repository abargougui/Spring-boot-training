package com.abargougui.springboottraining.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Department {

	private String name;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID uuid;

	@Version
	private int version;

	public Department() {
		super();
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

	public Department withUuid(UUID uuid) {
		return new Department(name, uuid);
	}

	public Department withName(String name) {
		return new Department(name, uuid);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
