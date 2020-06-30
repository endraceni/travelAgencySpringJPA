package ceni.endra.backend.service;

import java.util.List;

import ceni.endra.backend.beans.Destination;
import ceni.endra.backend.beans.Travel;

public interface TravelService {
	
	List<Travel> getAllTravels();
	
	Travel getTravelById(Travel request);
	
	List<Destination> checkDestinationExists(Travel request);
	
	int newTravel(Travel request);
	
	int deleteTravel(Travel request);

	Travel updateTravel(Travel request);


}
