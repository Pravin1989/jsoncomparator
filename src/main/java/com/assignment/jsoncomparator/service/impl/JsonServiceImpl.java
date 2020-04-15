package com.assignment.jsoncomparator.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.jsoncomparator.domain.Json;
import com.assignment.jsoncomparator.dto.JsonDto;
import com.assignment.jsoncomparator.exception.ResourceNotFoundException;
import com.assignment.jsoncomparator.mapper.JsonMapper;
import com.assignment.jsoncomparator.repository.JsonRepository;
import com.assignment.jsoncomparator.service.JsonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jsonpatch.diff.JsonDiff;

/**
 * @author Pravin
 *
 */
@Service("jsonService")
public class JsonServiceImpl implements JsonService {

	@Autowired
	private JsonRepository jsonRepository;

	@Autowired
	private JsonMapper jsonMapper;

	@Transactional
	@Override
	public JsonDto addJson(JsonNode jsonRequest) throws IOException {
		Json json = new Json();
		json.setJsonNode(jsonMapper.convertJsonNodeToByte(jsonRequest));
		Json savedJson = jsonRepository.save(json);
		return jsonMapper.convertToDto(savedJson);
	}

	@Override
	public JsonDto fetchJson(Long jsonId) {
		Optional<Json> json = jsonRepository.findById(jsonId);
		if (json.isPresent()) {
			return jsonMapper.convertToDto(json.get());
		}
		throw new ResourceNotFoundException("Json Object Not Found with Id : " + jsonId);
	}

	@Override
	public JsonDto removeJson(Long jsonId) {
		Optional<Json> json = jsonRepository.findById(jsonId);
		if (json.isPresent()) {
			jsonRepository.deleteById(jsonId);
		}
		throw new ResourceNotFoundException("Json Object Not Found to remove with Id : " + jsonId);
	}

	@Transactional
	@Override
	public boolean updateJson(Long jsonId, JsonNode jsonRequest) throws IOException {
		Optional<Json> json = jsonRepository.findById(jsonId);
		if (json.isPresent()) {
			jsonRepository.updateJson(jsonId, jsonMapper.convertJsonNodeToByte(jsonRequest));
			return true;
		}
		throw new ResourceNotFoundException("Json Object Not Found to update with Id : " + jsonId);
	}

	@Override
	public JsonNode compareJson(JsonDto jsonRequest) throws IOException {
		Optional<Json> json = jsonRepository.findById(jsonRequest.getJsonId());
		if (json.isPresent()) {
			jsonRequest.getInputJson();
			JsonNode source = jsonMapper.convertByteToJsonNode(json.get().getJsonNode());
			JsonNode difference = JsonDiff.asJson(source, jsonRequest.getInputJson());
			return filter(source, difference);
		}
		throw new ResourceNotFoundException(
				"Base Json Object Not Found with Id : " + jsonRequest.getJsonId() + " to compare");
	}

	private JsonNode filter(JsonNode source, JsonNode difference) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode objects = mapper.createArrayNode();
		difference.forEach(node -> {
			String operation = node.get("op").asText();
			String path = node.get("path").asText();
			if (operation.equals("add")) {
				String[] itemsPath = path.split("/");
				JsonNode jsonNode = node.get("value");
				JsonNode temp = null;
				String name = "";
				List<String> keys = new ArrayList<>();
				for (int i = 1; i < itemsPath.length; i++) {
					String key = itemsPath[i];
					if (source.get(key) != null) {
						temp = source.get(key);
					} else if (!StringUtils.isNumeric(key)) {
						keys.add(key);
					}

					if (null != temp && temp.isArray()) {
						if (StringUtils.isNumeric(key)) {
							temp = temp.get(Integer.valueOf(key));
							name = temp.get("name").asText();
						}
					}
					if (temp.get(key) != null) {
						temp = temp.get(key);
					}
					if (key.equals("-")) {
						name = jsonNode.get("name").asText();
					}
				}
				ObjectNode object = mapper.createObjectNode();
				ObjectNode prev = null;
				ObjectNode first = null;
				ObjectNode back = null;
				for (int i = keys.size() - 1; i >= 0; i--) {
					if (keys.size() - 1 == i) {
						first = object.set(keys.get(i), jsonNode);
					} else {
						back = prev;
						prev = mapper.createObjectNode();
						if (back == null)
							prev.set(keys.get(i), first);
						else {
							prev.set(keys.get(i), back);
						}
					}
				}
				ObjectNode obj = mapper.createObjectNode();
				if (keys.get(0).equals("-")) {
					obj.set(name, jsonNode);
				} else
					obj.set(name, prev);
				objects.add(obj);
			}
			if (operation.equals("remove")) {
				String[] pathItems = path.split("/");
				String name = "";
				JsonNode temp = null;
				List<String> keyList = new ArrayList<>();
				boolean isNameFound = false;
				for (int j = 1; j < pathItems.length; j++) {
					String key = pathItems[j];
					JsonNode jsonNode = source.get(key);
					if (jsonNode != null) {
						temp = jsonNode;
					} else {

					}
					if (null != temp && temp.isArray()) {
						if (StringUtils.isNumeric(key)) {
							temp = temp.get(Integer.valueOf(key));
							name = temp.get("name").asText();
							isNameFound = true;
						}
					}
					if (isNameFound && !StringUtils.isNumeric(key)) {
						keyList.add(key);
					}
					if (temp.get(key) != null) {
						temp = temp.get(key);
					}
				}
				ObjectNode object = mapper.createObjectNode();
				ObjectNode prev = null;
				ObjectNode first = null;
				ObjectNode back = null;
				for (int i = keyList.size() - 1; i >= 0; i--) {
					if (keyList.size() - 1 == i) {
						first = object.set(keyList.get(i), temp);
					} else {
						back = prev;
						prev = mapper.createObjectNode();
						if (back == null)
							prev.set(keyList.get(i), first);
						else {
							prev.set(keyList.get(i), back);
						}
					}
				}
				ObjectNode obj = mapper.createObjectNode();
				obj.set(name, prev);
				objects.add(obj);
			}
		});
		return objects;
	}
}