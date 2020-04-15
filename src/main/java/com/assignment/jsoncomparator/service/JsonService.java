package com.assignment.jsoncomparator.service;

import java.io.IOException;

import com.assignment.jsoncomparator.dto.JsonDto;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Pravin
 *
 */
public interface JsonService {
	JsonDto addJson(JsonNode jsonRequest) throws IOException;

	JsonDto fetchJson(Long jsonId);

	boolean updateJson(Long jsonId, JsonNode jsonRequest) throws IOException;

	JsonDto removeJson(Long jsonId);

	JsonNode compareJson(JsonDto jsonRequest) throws IOException;
}
