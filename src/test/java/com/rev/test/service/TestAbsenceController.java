package com.rev.test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rev.model.AbsenceModel;

import org.junit.Assert;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-application-context.xml"})
@WebAppConfiguration
class TestAbsenceController {
	
	@Autowired
	WebApplicationContext wac;
	private MockMvc mockMvc;
	
	
	private List<AbsenceModel> list = new ArrayList<AbsenceModel>();
	
	
	@Before
	private void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		list.add(new AbsenceModel(1, 100, new Date(System.currentTimeMillis()), 2));
		list.add(new AbsenceModel(2, 101, new Date(System.currentTimeMillis()), 3));
		list.add(new AbsenceModel(3, 102, new Date(System.currentTimeMillis()), 2));
	}

	@Test
	void testGetAllAbsences() throws Exception{
		MvcResult result = mockMvc.perform(get("/absences"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		Assert.assertNotNull(result);
		Assert.assertTrue("Empty content", result.getResponse().getContentAsString().length() > 0);
	}

	@Test
	void testGetAbsenceById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateAbsence() {
		fail("Not yet implemented");
	}

	@Test
	void testAddAbsence() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteAbsence() {
		fail("Not yet implemented");
	}

}
