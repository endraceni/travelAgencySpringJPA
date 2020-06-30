package ceni.endra.backend.service;

import java.util.List;

import ceni.endra.backend.beans.Destination;
import ceni.endra.backend.repository.DestinationDao;

public interface DestinationService {
	
	public List<Destination> getAllDestinations();
	
	Destination getDestinationById(Destination request);
	
	int insertIntoDestinations(Destination request);
	
	int deleteDestination(Destination request);

	Destination updateDestination(Destination request);

	List<Destination> getTravelbyDest(Destination request);
}
