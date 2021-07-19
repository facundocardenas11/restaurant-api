package restaurant.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import restaurant.api.exceptions.AppException;
import restaurant.api.jsons.RestaurantRest;

@Service //Declaro Bean del Servicio.
public interface RestaurantService {

	   RestaurantRest getRestaurantById(Long restaurantId) throws AppException;
	   
	   public List<RestaurantRest> getRestaurants() throws AppException;
}
