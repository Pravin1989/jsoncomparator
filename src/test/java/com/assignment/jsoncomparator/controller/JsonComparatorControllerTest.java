package com.assignment.jsoncomparator.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assignment.jsoncomparator.dto.JsonDto;
import com.assignment.jsoncomparator.service.JsonService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JsonComparatorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JsonService jsonService;

	@Autowired
	private ObjectMapper mapper;

	@Before
	public void setup() {
	}

	@Test
	public void validateCreateJSONObject() throws Exception {
		String inputPayload = "{\"Division\":{\"fields\":{\"deptId\":{\"name\":\"deptId\",\"label\":\"Department ID \",\"description\":\"Department Id\",\"localizedFlag\":false}}}}";
		JsonNode json = mapper.readValue(inputPayload, JsonNode.class);
		JsonDto response = new JsonDto();
		response.setInputJson(json);
		response.setJsonId(1L);
		
		when(jsonService.addJson(json)).thenReturn(response);

		mockMvc.perform(MockMvcRequestBuilders.post("/jsoncomparator").content(inputPayload)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.jsonId").exists());
	}
}
