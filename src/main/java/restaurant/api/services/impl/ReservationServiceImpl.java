package restaurant.api.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant.api.entities.Reservation;
import restaurant.api.entities.Restaurant;
import restaurant.api.entities.Turn;
import restaurant.api.exceptions.AppException;
import restaurant.api.exceptions.InternalServerErrorException;
import restaurant.api.exceptions.NotFountException;
import restaurant.api.jsons.CreateReservationRest;
import restaurant.api.jsons.ReservationRest;
import restaurant.api.repository.ReservationRepository;
import restaurant.api.repository.RestaurantRepository;
import restaurant.api.repository.TurnRepository;
import restaurant.api.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private RestaurantRepository restaurantRepo;

	@Autowired
	private TurnRepository turnRepo;

	@Autowired
	private ReservationRepository rrepo;

	public static final ModelMapper modelMapper = new ModelMapper();

	public String createReservation(CreateReservationRest createR) throws AppException {

		final Restaurant restaurantId = restaurantRepo.findById(createR.getRestaurantId())
				.orElseThrow(() -> new NotFountException("RESTAURANT_NOT_FOUND", "RESTAURANT_NOT_FOUND"));

		String locator = generateLocator(restaurantId, createR);

		final Turn turnId = turnRepo.findById(createR.getTurnId())
				.orElseThrow(() -> new NotFountException("TURN_NOT_FOUND", "TURN_NOT_FOUND"));

		final Reservation re = new Reservation();
		re.setLocator(locator);
		re.setPerson(createR.getPerson());
		re.setDate(createR.getDate());
		re.setRestaurant(restaurantId);
		re.setTurn(turnId.getName());

		try {
			rrepo.save(re);
		} catch (final Exception e) {

			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}

		return locator;
	}

	private String generateLocator(Restaurant restaurantId, CreateReservationRest createR) throws AppException {
		return restaurantId.getName() + createR.getTurnId();
	}

	public ReservationRest getReservationById(Long reservationId) throws AppException {
		return modelMapper.map(getReservationEntity(reservationId), ReservationRest.class);

	}

	private Reservation getReservationEntity(Long reservationId) throws AppException {
		return rrepo.findById(reservationId)
				.orElseThrow(() -> new NotFountException("SNOT-404", "RESTAURANT_NOT_FOUND"));
	}

}
