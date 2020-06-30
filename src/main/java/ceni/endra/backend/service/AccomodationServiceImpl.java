package ceni.endra.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceni.endra.backend.beans.Accomodation;
import ceni.endra.backend.beans.Destination;
import ceni.endra.backend.beans.Travel;
import ceni.endra.backend.repository.AccomodationDao;

@Service("accomodationServiceImpl")
public class AccomodationServiceImpl implements AccomodationService{
	
	@Autowired
	private AccomodationDao accomodationDao;

	@Override
	public List<Accomodation> getAllAccomodations() {
		
		return accomodationDao.findAll();
	}

	@Override
	public Accomodation getAccomodationById(Accomodation request) {

		try {
			Accomodation accmd = accomodationDao.findById(request.getIdAccomodation()).get();
		if(accmd.equals(null)) {
			return null;
		}
		
		 return accmd;
		}
		catch(NoSuchElementException ex) {
			return null;
		}
		

	}

	@Override
	public int insertIntoAccomodations(Accomodation request) {
		
		Accomodation accmd = new Accomodation();
		accmd.setIdAccomodation(request.getIdAccomodation());
		accmd.setAccomodationType(request.getAccomodationType());
		accmd.setAddress(request.getAddress());
		accmd.setCost(request.getCost());
		accmd.setDestination(request.getDestination());
		
		//kthejme id-ne e destination te deshiruar
		return accomodationDao.saveAndFlush(accmd).getIdAccomodation();
	}

	@Override
	public int deleteAccomodation(Accomodation request) {
		Accomodation accmd = getAccomodationById(request);
		if(accmd != null) {
			
			accomodationDao.delete(accmd);
		}
		else {
			return 0;
		}
		
		return 1;

	}

	@Override
	public Accomodation updateAccomodation(Accomodation request) {
		Accomodation accmd = getAccomodationById(request);
		if(accmd != null) {
			
			accmd.setAccomodationType(request.getAccomodationType());
			accmd.setAddress(request.getAddress());
			accmd.setCost(request.getCost());
			accmd.setDestination(request.getDestination());
			
			accomodationDao.saveAndFlush(accmd);
		}
		else {
			return null;
		}
		
		return accmd;

	}

}
