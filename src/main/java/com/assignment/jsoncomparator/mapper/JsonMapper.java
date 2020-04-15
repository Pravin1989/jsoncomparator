package com.assignment.jsoncomparator.mapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.assignment.jsoncomparator.domain.Json;
import com.assignment.jsoncomparator.dto.JsonDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author Pravin
 *
 */
@Mapper(componentModel = "spring")
public interface JsonMapper {
	ObjectMapper objectMapper = new ObjectMapper();

	@Mapping(source = "jsonNode", target = "inputJson", qualifiedByName = "convertByteToJsonNode")
	public JsonDto convertToDto(Json json);

	public default JsonNode convertByteToJsonNode(byte[] byteResponse) throws IOException {
		ObjectReader reader = objectMapper.reader();
		return reader.readTree(new ByteArrayInputStream(byteResponse));
	}

	public default byte[] convertJsonNodeToByte(JsonNode jsonRequest) throws IOException {
		ObjectWriter writer = objectMapper.writer();
		return writer.writeValueAsBytes(jsonRequest);
	}
}
