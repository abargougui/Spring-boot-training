package com.abargougui.springboottraining.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@RequestMapping
	public LocalDateTime sayTheTime() {
		return LocalDateTime.now();
	}

	@RequestMapping(path = "many")
	public String sayTheTimeMany(@RequestParam String name, @RequestParam(defaultValue = "10") int rep) {
		return IntStream.rangeClosed(1, rep).mapToObj(i -> i + ". Hello " + name + "! Now it is " + LocalDateTime.now())
				.collect(Collectors.joining("\n"));
	}

	@RequestMapping(path = "manyParams")
	public String sayTheTimeManyParams(Params params) {
		return IntStream.rangeClosed(1, params.getRep())
				.mapToObj(i -> i + ". Hello " + params.getName() + "! Now it is " + LocalDateTime.now())
				.collect(Collectors.joining("\n"));
	}

	@RequestMapping(path = "many/{name}/{rep}")
	public String sayTheTimeManyParamsPath(Params params) {
		return IntStream.rangeClosed(1, params.getRep())
				.mapToObj(i -> i + ". Hello " + params.getName() + "! Now it is " + LocalDateTime.now())
				.collect(Collectors.joining("\n"));
	}

	@RequestMapping(path = "many2/{name}/{rep}")
	public String sayTheTimeManyPath(@PathVariable String name, @PathVariable int rep) {
		return IntStream.rangeClosed(1, rep).mapToObj(i -> i + ". Hello " + name + "! Now it is " + LocalDateTime.now())
				.collect(Collectors.joining("\n"));
	}

	@PostMapping(path = "manyParams")
	public String sayTheTimeManyParamsPost(@RequestBody Params params) {
		String str = IntStream.rangeClosed(1, params.getRep())
				.mapToObj(i -> i + ". Hello " + params.getName() + "! Now it is " + LocalDateTime.now())
				.collect(Collectors.joining("\n"));

		return str;
	}

	static class Params {
		String name;
		int rep;

		public Params(String name, int rep) {
			super();
			this.name = name;
			this.rep = rep;
		}

		public Params() {
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getRep() {
			return rep;
		}

		public void setRep(int rep) {
			this.rep = rep;
		}
	}

}
