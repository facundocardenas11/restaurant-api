package restaurant.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant.api.entities.Restaurant;
import restaurant.api.exceptions.AppException;
import restaurant.api.exceptions.NotFountException;
import restaurant.api.jsons.RestaurantRest;
import restaurant.api.repository.RestaurantRepository;
import restaurant.api.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository repo;

	public static final ModelMapper modelMapper = new ModelMapper();

	public RestaurantRest getRestaurantById(Long restaurantId) throws AppException {
		return modelMapper.map(getRestaurantEntity(restaurantId), RestaurantRest.class);
	}

	public List<RestaurantRest> getRestaurants() throws AppException {
		final List<Restaurant> restaurantEntity = repo.findAll();

		return restaurantEntity.stream().map(service -> modelMapper.map(service, RestaurantRest.class))
				.collect(Collectors.toList());
	}

	private Restaurant getRestaurantEntity(Long restaurantId) throws AppException {
		return repo.findById(restaurantId).orElseThrow(() -> new NotFountException("SNOT-404", "RESTAURANT_NOT_FOUND"));
	}

}
