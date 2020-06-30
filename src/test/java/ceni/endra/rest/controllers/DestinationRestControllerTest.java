package ceni.endra.rest.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.fasterxml.jackson.annotation.JsonInclude;
 
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.webservices.internal.api.message.ContentType;
import com.sun.xml.internal.stream.Entity;

import ceni.endra.backend.beans.Destination;
import ceni.endra.backend.repository.DestinationDao;
import ceni.endra.backend.service.DestinationServiceImpl;

class DestinationRestControllerTest {
	
	@InjectMocks
	DestinationRestController destinationRestController;
	
	@Mock
	DestinationServiceImpl destinationServiceImpl;


	@BeforeEach
	void setUp() throws Exception {
		Destination mockDest = new Destination();
		mockDest.settName("Kanioni Holtes");
		mockDest.settCity("Elbaasan");
		mockDest.settState("Albania");
		Optional<Destination> optDest = Optional.of(mockDest);
		
	}

	private MockMvc mockMvc;
	@Test
	void testGetSpecificDestination() throws IOException, Exception {
		Destination mockDest = new Destination();
		mockDest.settName("Kanioni Holtes");
		mockDest.settCity("Elbaasan");
		mockDest.settState("Albania");
		Optional<Destination> optDest = Optional.of(mockDest);
	 
	        mockMvc.perform(get("/destination/getSpecificDestination")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(DestinationRestControllerTest.convertObjectToJsonBytes(mockDest))
	        )
	                .andExpect(status().isBadRequest())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$.fieldErrors", hasSize(2)))
	                .andExpect(jsonPath("$.fieldErrors[*].path", containsInAnyOrder("title", "description")))
	                .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder(
	                        "The maximum length of the description is 500 characters.",
	                        "The maximum length of the title is 100 characters."
	                )));
	 
	        verifyZeroInteractions(mockDest);
	}

	 public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	        return mapper.writeValueAsBytes(object);
	    }
	 
	    public static String createStringWithLength(int length) {
	        StringBuilder builder = new StringBuilder();
	 
	        for (int index = 0; index < length; index++) {
	            builder.append("a");
	        }
	 
	        return builder.toString();
	    }
}
