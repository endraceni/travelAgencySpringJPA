package ceni.endra.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceni.endra.backend.beans.Destination;
import ceni.endra.backend.repository.DestinationDao;

@Service("destinationServiceImpl")
public class DestinationServiceImpl implements DestinationService{
	
	@Autowired
	DestinationDao destinationDao;

	@Override
	public List<Destination> getAllDestinations() {

		List<Destination> list = destinationDao.findAll();
		     
        return list;
  
	}

	@Override
	public Destination getDestinationById(Destination request) {

		try {
		Destination aDestination = destinationDao.findById(request.getnIdDestination()).get();
		if(aDestination.equals(null)) {
			return null;
		}
		
		 return aDestination;
		}
		catch(NoSuchElementException ex) {
			return null;
		}
		
	}

	@Override
	public List<Destination> getTravelbyDest(Destination request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertIntoDestinations(Destination request) {
		
		Destination newDest = new Destination();
		newDest.setnIdDestination(request.getnIdDestination());
		newDest.settName(request.gettName());
		newDest.settCity(request.gettCity());
		newDest.settState(request.gettState());
		newDest.setdDelete("0");
		
		//kthejme id-ne e destination te deshiruar
		return destinationDao.saveAndFlush(newDest).getnIdDestination();
	}

	
	//logic delete 
	@Override
	public int deleteDestination(Destination request) {
		
		Destination aDest = getDestinationById(request);
		if(aDest != null) {
			aDest.setdDelete("1");
			
			destinationDao.saveAndFlush(aDest);
		}
		else {
			return 0;
		}
		
		return 1;
	}

	@Override
	public Destination updateDestination(Destination request) {
		
		Destination aDest = getDestinationById(request);
		if(aDest != null) {
			aDest.settName(request.gettName());
			aDest.settCity(request.gettCity());
			aDest.settState(request.gettState());
			
			destinationDao.saveAndFlush(aDest);
		}
		else {
			return null;
		}
		
		return aDest;
	}

}
