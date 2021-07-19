package restaurant.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restaurant.api.exceptions.AppException;
import restaurant.api.jsons.CreateReservationRest;
import restaurant.api.jsons.ReservationRest;
import restaurant.api.responses.AppResponse;
import restaurant.api.services.ReservationService;

@RestController
@CrossOrigin(origins = "http//:localhost:4200")
@RequestMapping(path = "/app")
public class ReservationController {	

	@Autowired
	private ReservationService reservationService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/reservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AppResponse<String> createReservation(@RequestBody CreateReservationRest c) throws AppException {
		return new AppResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				reservationService.createReservation(c)); 
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/reservationsById/{reservationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AppResponse<ReservationRest> getReservationById(@PathVariable Long reservationId) throws AppException {
		return new AppResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				reservationService.getReservationById(reservationId));
	}
}