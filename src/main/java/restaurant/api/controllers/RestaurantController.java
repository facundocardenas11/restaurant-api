package restaurant.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import restaurant.api.exceptions.AppException;
import restaurant.api.jsons.RestaurantRest;
import restaurant.api.responses.AppResponse;
import restaurant.api.services.RestaurantService;

@RestController
@CrossOrigin(origins = "http//:localhost:4200")
@RequestMapping(path = "/app")
public class RestaurantController {

	@Autowired
	private RestaurantService rService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/restaurantById" + "/{" + "restaurantId"
			+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AppResponse<RestaurantRest> getRestaurantById(@PathVariable Long restaurantId) throws AppException {

		return new AppResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
				rService.getRestaurantById(restaurantId));

	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/restaurantsAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AppResponse<List<RestaurantRest>> getRestaurants() throws AppException {
		return new AppResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", rService.getRestaurants());
	}

}
