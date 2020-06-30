package ceni.endra.backend.service;

import java.util.List;

import ceni.endra.backend.beans.Accomodation;
import ceni.endra.backend.beans.Destination;

public interface AccomodationService {
	
	
	List<Accomodation> getAllAccomodations();
	
	Accomodation getAccomodationById(Accomodation request);
	
	int insertIntoAccomodations(Accomodation request);
	
	int deleteAccomodation(Accomodation request);

	Accomodation updateAccomodation(Accomodation request);

}
