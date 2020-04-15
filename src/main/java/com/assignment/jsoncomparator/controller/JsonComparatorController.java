package com.assignment.jsoncomparator.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.jsoncomparator.dto.JsonDto;
import com.assignment.jsoncomparator.service.JsonService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Pravin
 *
 */
@RequestMapping("/jsoncomparator")
@RestController
public class JsonComparatorController {

	@Autowired
	private JsonService jsonService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonDto> addJson(@RequestBody JsonNode jsonRequest) throws IOException {
		JsonDto addedJson = jsonService.addJson(jsonRequest);
		return new ResponseEntity<JsonDto>(addedJson, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{json_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonDto> getJson(@PathVariable("json_id") Long jsonId) {
		JsonDto json = jsonService.fetchJson(jsonId);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@PutMapping(value = "/{json_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateJson(@PathVariable("json_id") Long jsonId, @RequestBody JsonNode jsonRequest)
			throws IOException {
		jsonService.updateJson(jsonId, jsonRequest);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{json_id}")
	public ResponseEntity<JsonDto> removeJson(@PathVariable("json_id") Long jsonId) {
		JsonDto removedJson = jsonService.removeJson(jsonId);
		return new ResponseEntity<JsonDto>(removedJson, HttpStatus.OK);
	}

	@PostMapping(value = "/compare-jsons", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> compareJson(@RequestBody JsonDto jsonRequest) throws IOException {
		JsonNode diffJson = jsonService.compareJson(jsonRequest);
		return new ResponseEntity<JsonNode>(diffJson, HttpStatus.OK);
	}
}
