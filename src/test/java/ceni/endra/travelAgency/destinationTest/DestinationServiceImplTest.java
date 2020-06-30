package ceni.endra.travelAgency.destinationTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ceni.endra.backend.beans.Destination;
import ceni.endra.backend.repository.DestinationDao;
import ceni.endra.backend.service.DestinationServiceImpl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

class DestinationServiceImplTest {
	
	@InjectMocks
	DestinationServiceImpl destinationServiceImpl;
	
	@Mock
	DestinationDao destinationDao;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetDestinationById() {
		Destination mockDest = new Destination();
		mockDest.settName("Kanioni Holtes");
		mockDest.settCity("Elbaasan");
		mockDest.settState("Albania");
		Optional<Destination> optDest = Optional.of(mockDest);
		when( destinationDao.findById( anyInt() ) ).thenReturn(optDest);
		
		Destination aDest = destinationServiceImpl.getDestinationById(mockDest);
		assertEquals("Kanioni Holtes", mockDest.gettName());
	}
	
	
	

//	@Test
//	void testGetAllDestinations() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testInsertIntoDestinations() {
//		fail("Not yet implemented");
//	}
//
	@Test
	void testDeleteDestination() {
		Destination mockDest = new Destination();
		mockDest.settName("Kanioni Holtes");
		mockDest.settCity("Elbaasan");
		mockDest.settState("Albania");
		Optional<Destination> optDest = Optional.of(mockDest);
		destinationDao.saveAndFlush( mockDest );
		
		int answ = destinationServiceImpl.deleteDestination(mockDest);
		assertEquals(1, anyInt());
	}
//
//	@Test
//	void testUpdateDestination() {
//		fail("Not yet implemented");
//	}

}
