package ceni.endra.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceni.endra.backend.beans.Destination;
import ceni.endra.backend.beans.Travel;
import ceni.endra.backend.repository.TravelDao;

@Service("travelServiceImpl")
public class TravelServiceImpl implements TravelService{
	
	@Autowired
	TravelDao travelDao;

	@Override
	public List<Travel> getAllTravels() {
		
		List<Travel> list = travelDao.findAll();
	     
        return list;
	}

	@Override
	public Travel getTravelById(Travel request) {

		try {
			Travel aTravel = travelDao.findById(request.getIdTravel()).get();
			if(aTravel.equals(null)) {
				return null;
			}
			
			 return aTravel;
			}
			catch(NoSuchElementException ex) {
				return null;
			}
			

		
	}

	@Override
	public List<Destination> checkDestinationExists(Travel request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int newTravel(Travel request) {
		
		Travel newTravel = new Travel();
		newTravel.setIdTravel(request.getIdTravel());
		newTravel.setDestination(request.getDestination());
		newTravel.setdStart(request.getdStart());
		newTravel.setdEnd(request.getdEnd());
		newTravel.setdDelete("0");
		
		//kthejme id-ne e destination te deshiruar
		return travelDao.saveAndFlush(newTravel).getIdTravel();
		
	}

	@Override
	public int deleteTravel(Travel request) {
		
		Travel aTravel = getTravelById(request);
		if(aTravel != null) {
			aTravel.setdDelete("1");
			
			travelDao.saveAndFlush(aTravel);
		}
		else {
			return 0;
		}
		
		return 1;

	}

	@Override
	public Travel updateTravel(Travel request) {
	
		Travel aTravel = getTravelById(request);
		if(aTravel != null) {
			aTravel.setDestination(request.getDestination());
			aTravel.setdStart(request.getdStart());
			aTravel.setdEnd(request.getdEnd());
			
			travelDao.saveAndFlush(aTravel);
		}
		else {
			return null;
		}
		
		return aTravel;
		
	}
	

}
