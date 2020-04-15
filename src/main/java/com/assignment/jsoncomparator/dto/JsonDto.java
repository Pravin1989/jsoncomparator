package com.assignment.jsoncomparator.dto;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

/**
 * @author Pravin
 *
 */
@Data
public class JsonDto {
	private Long jsonId;

	public Long getJsonId() {
		return jsonId;
	}

	public void setJsonId(Long jsonId) {
		this.jsonId = jsonId;
	}

	public JsonNode getInputJson() {
		return inputJson;
	}

	public void setInputJson(JsonNode inputJson) {
		this.inputJson = inputJson;
	}

	private JsonNode inputJson;
}
