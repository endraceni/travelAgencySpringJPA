package ceni.endra.rest.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ceni.endra.backend.beans.Destination;
import ceni.endra.backend.beans.Travel;
import ceni.endra.backend.service.DestinationService;
import ceni.endra.backend.service.TravelService;
import ceni.endra.util.ResultDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/travel")
public class TravelRestController {
	
	@Autowired
	private TravelService travelService;

	private static final Logger log = LogManager.getLogger(TravelRestController.class.getName());
	
	@RequestMapping(value = "/getTravelsList", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<ResultDTO<List<Travel>>> getTravelsList(){
		
		ResultDTO<List<Travel>> risultato = new ResultDTO<List<Travel>>();
		
		ResultDTO<Travel> response = new ResultDTO<Travel>();
		List<Travel> travelList = new ArrayList<Travel>();
		travelList = travelService.getAllTravels();
		
		if (travelList.size() != 0) {
			risultato.success();
			risultato.setData(travelList);
		} else {
			risultato.addError("No travels found");
		}

	return ResponseEntity.ok(risultato);
	
	}
	
	@RequestMapping(value = "/insertTravel", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Integer>> insertTravel(@RequestBody Travel request)
	{

		log.info("Application started");
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		
		log.info("Outside if");
		if (request.getdStart() != null) {
			log.info("inside if-statement");
			int inserted = travelService.newTravel(request);
			
			log.info("inserted value", inserted);
			if (inserted > 0) {
				result.setData(inserted);
				result.success();
			} else {
				result.addError("Something went wrong ! Try again. ");
			}
		} else {
			result.addError("Please check again the data entered");
		}
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/getSpecificTravel", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Travel>> getSpecificTravel(@RequestBody Travel request)
	{

		log.info("Application started");
		ResultDTO<Travel> result = new ResultDTO<Travel>();
		
		log.info("Outside if");
		if (request.getIdTravel() != null && request.getIdTravel() != 0 && request.getDestination().getnIdDestination() != null && request.getDestination().getnIdDestination() != 0 ) {
			log.info("inside if-statement");
			Travel travelDetails = travelService.getTravelById(request);
			
			if ( travelDetails != null) {
				log.info("Travel:",travelDetails);
				result.setData(travelDetails);
				result.success();
			} else {
				result.addError("Something went wrong ! Try again. ");
			}
		} else {
			result.addError("Please check again the data entered");
		}
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/updateTravel", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Travel>> updateTravel(@RequestBody Travel request)
	{

		log.info("Application started");
		ResultDTO<Travel> result = new ResultDTO<Travel>();
		
		log.info("Outside if");
		if (request.getDestination().getnIdDestination() != null && request.getDestination().getnIdDestination() != 0) {
			log.info("inside if-statement");
			Travel travelDetails = travelService.updateTravel(request);
			
			if ( travelDetails != null) {
				log.info("Destination:",travelDetails);
				result.setData(travelDetails);
				result.success();
			} else {
				result.addError("Travel does not exist");
			}
		} else {
			result.addError("Please check again the data entered");
		}
		return ResponseEntity.ok(result);
	}
	
	
	@RequestMapping(value = "/deleteTravel", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Integer>> deleteTravel(@RequestBody Travel request)
	{

		log.info("Application started");
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		
		log.info("Outside if");
		if (request.getIdTravel() != null && request.getIdTravel() != 0) {
			log.info("inside if-statement");
			int deleted = travelService.deleteTravel(request);
			
			if ( deleted == 1) {
				log.info("Travel u fshi");
				result.setData(deleted);
				result.success();
			} else {
				result.setData(deleted);
				result.addInfo("Travel could not be deleted");
			}
		} else {
			result.addError("Please check again the data entered");
		}
		return ResponseEntity.ok(result);
	}


}
