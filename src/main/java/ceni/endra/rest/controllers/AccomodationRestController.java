package ceni.endra.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ceni.endra.backend.beans.Accomodation;
import ceni.endra.backend.service.AccomodationService;
import ceni.endra.util.ResultDTO;

@RestController
@RequestMapping(value = "/accomodation")
public class AccomodationRestController {
	

	private AccomodationService accomodationService;

	private static final Logger log = LogManager.getLogger(TravelRestController.class.getName());
	
	@RequestMapping(value = "/getAccomodationsList", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<ResultDTO<List<Accomodation>>> getAccomodationsList(){
		
		ResultDTO<List<Accomodation>> risultato = new ResultDTO<List<Accomodation>>();
		
		ResultDTO<Accomodation> response = new ResultDTO<Accomodation>();
		List<Accomodation> accomodationList = new ArrayList<Accomodation>();
		accomodationList = accomodationService.getAllAccomodations();
		
		if (accomodationList.size() != 0) {
			risultato.success();
			risultato.setData(accomodationList);
		} else {
			risultato.addError("No Accomodations found");
		}

	return ResponseEntity.ok(risultato);
	
	}
	
	@RequestMapping(value = "/insertAccomodation", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Integer>> insertAccomodation(@RequestBody Accomodation request)
	{

		log.info("Application started");
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		
		log.info("Outside if");
		if (request.getDestination().getnIdDestination() != null && request.getDestination().getnIdDestination() != 0) {
			log.info("inside if-statement");
			int inserted = accomodationService.insertIntoAccomodations(request);
			
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
	
	@RequestMapping(value = "/getSpecificAccomodation", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Accomodation>> getSpecificAccomodation(@RequestBody Accomodation request)
	{

		log.info("Application started");
		ResultDTO<Accomodation> result = new ResultDTO<Accomodation>();
		
		log.info("Outside if");
		if (request.getIdAccomodation() != null && request.getIdAccomodation() != 0 && request.getDestination().getnIdDestination() != null && request.getDestination().getnIdDestination() != 0 ) {
			log.info("inside if-statement");
			Accomodation accomodationDetails = accomodationService.getAccomodationById(request);
			
			if ( accomodationDetails != null) {
				log.info("Accomodation:",accomodationDetails);
				result.setData(accomodationDetails);
				result.success();
			} else {
				result.addError("Something went wrong ! Try again. ");
			}
		} else {
			result.addError("Please check again the data entered");
		}
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/updateAccomodation", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Accomodation>> updateAccomodation(@RequestBody Accomodation request)
	{

		log.info("Application started");
		ResultDTO<Accomodation> result = new ResultDTO<Accomodation>();
		
		log.info("Outside if");
		if (request.getDestination().getnIdDestination() != null && request.getDestination().getnIdDestination() != 0) {
			log.info("inside if-statement");
			Accomodation accomodationDetails = accomodationService.updateAccomodation(request);
			
			if ( accomodationDetails != null) {
				log.info("Destination:",accomodationDetails);
				result.setData(accomodationDetails);
				result.success();
			} else {
				result.addError("Accomodation does not exist");
			}
		} else {
			result.addError("Please check again the data entered");
		}
		return ResponseEntity.ok(result);
	}
	
	
	@RequestMapping(value = "/deleteAccomodation", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Integer>> deleteAccomodation(@RequestBody Accomodation request)
	{

		log.info("Application started");
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		
		log.info("Outside if");
		if (request.getIdAccomodation() != null && request.getIdAccomodation() != 0) {
			log.info("inside if-statement");
			int deleted = accomodationService.deleteAccomodation(request);
			
			if ( deleted == 1) {
				log.info("Accomodation u fshi");
				result.setData(deleted);
				result.success();
			} else {
				result.setData(deleted);
				result.addInfo("Accomodation could not be deleted");
			}
		} else {
			result.addError("Please check again the data entered");
		}
		return ResponseEntity.ok(result);
	}


	
}
