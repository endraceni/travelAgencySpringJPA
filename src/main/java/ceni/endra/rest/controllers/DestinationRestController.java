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
import ceni.endra.backend.service.DestinationService;
import ceni.endra.util.ResultDTO;
import io.micrometer.core.annotation.Timed;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/destination")
public class DestinationRestController {
	
	@Autowired
	private DestinationService destinationService;

	private static final Logger log = LogManager.getLogger(DestinationRestController.class.getName());
	
	
	@Timed(
            value = "destination.getDestinationsList.request",
            histogram = true,
            percentiles = {0.95, 0.99},
            extraTags = {"version", "1.0"}
    )
	@RequestMapping(value = "/getDestinationsList", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<ResultDTO<List<Destination>>> getDestinationsList(){
		
		ResultDTO<List<Destination>> risultato = new ResultDTO<List<Destination>>();
		
		ResultDTO<Destination> response = new ResultDTO<Destination>();
		List<Destination> destinationsList = new ArrayList<Destination>();
		destinationsList = destinationService.getAllDestinations();
		
		if (destinationsList.size() != 0) {
			risultato.success();
			risultato.setData(destinationsList);
		} else {
			risultato.addError("No destinations found");
		}

	return ResponseEntity.ok(risultato);
	
	}
	
	@RequestMapping(value = "/insertDestination", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Integer>> insertDestination(@RequestBody Destination request)
	{

		log.info("Application started");
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		
		log.info("Outside if");
		if (!request.gettName().trim().equals("") && request.gettName() != null
				&& !request.gettState().trim().equals("") && request.gettState() != null) {
			log.info("inside if-statement");
			int inserted = destinationService.insertIntoDestinations(request);
			
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
	
	@Timed(
            value = "destination.getSpecificDestination.request",
            histogram = true,
            percentiles = {0.95, 0.99},
            extraTags = {"version", "1.0"}
    )	
	@RequestMapping(value = "/getSpecificDestination", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Destination>> getSpecificDestination(@RequestBody Destination request)
	{

		log.info("Application started");
		ResultDTO<Destination> result = new ResultDTO<Destination>();
		
		log.info("Outside if");
		if (request.getnIdDestination() != null && request.getnIdDestination() != 0) {
			log.info("inside if-statement");
			Destination destDetails = destinationService.getDestinationById(request);
			
			if ( destDetails != null) {
				log.info("Destination:",destDetails);
				result.setData(destDetails);
				result.success();
			} else {
				result.addError("Something went wrong ! Try again. ");
			}
		} else {
			result.addError("Please check again the data entered");
		}
		return ResponseEntity.ok(result);
	}

	@RequestMapping(value = "/updateDestination", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Destination>> updateDestination(@RequestBody Destination request)
	{

		log.info("Application started");
		ResultDTO<Destination> result = new ResultDTO<Destination>();
		
		log.info("Outside if");
		if (request.getnIdDestination() != null && request.getnIdDestination() != 0) {
			log.info("inside if-statement");
			Destination destDetails = destinationService.updateDestination(request);
			
			if ( destDetails != null) {
				log.info("Destination:",destDetails);
				result.setData(destDetails);
				result.success();
			} else {
				result.addError("Destination does not exist");
			}
		} else {
			result.addError("Please check again the data entered");
		}
		return ResponseEntity.ok(result);
	}
	
	
	@RequestMapping(value = "/deleteDestination", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<ResultDTO<Integer>> deleteDestination(@RequestBody Destination request)
	{

		log.info("Application started");
		ResultDTO<Integer> result = new ResultDTO<Integer>();
		
		log.info("Outside if");
		if (request.getnIdDestination() != null && request.getnIdDestination() != 0) {
			log.info("inside if-statement");
			int deleted = destinationService.deleteDestination(request);
			
			if ( deleted == 1) {
				log.info("Destination u fshi");
				result.setData(deleted);
				result.success();
			} else {
				result.setData(deleted);
				result.addInfo("Destination could not be deleted");
			}
		} else {
			result.addError("Please check again the data entered");
		}
		return ResponseEntity.ok(result);
	}

	
}
