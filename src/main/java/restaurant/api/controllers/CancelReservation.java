package restaurant.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restaurant.api.exceptions.AppException;
import restaurant.api.responses.AppResponse;
import restaurant.api.services.CancelReservationService;

@RestController
@CrossOrigin(origins = "http//:localhost:4200")
@RequestMapping(path = "/app")
public class CancelReservation {

	@Autowired
	private CancelReservationService cService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/deleteReservation", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AppResponse<String> createReservation(@RequestParam String locator) throws AppException {
		return new AppResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", cService.deleteReservation(locator));
	}

}
