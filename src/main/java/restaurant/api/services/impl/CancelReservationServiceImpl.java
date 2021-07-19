package restaurant.api.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant.api.exceptions.AppException;
import restaurant.api.exceptions.InternalServerErrorException;
import restaurant.api.exceptions.NotFountException;
import restaurant.api.repository.ReservationRepository;
import restaurant.api.services.CancelReservationService;


@Service
public class CancelReservationServiceImpl implements CancelReservationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private ReservationRepository reservationRepo;

	@Override
	public String deleteReservation(String locator) throws AppException {
		reservationRepo.findByLocator(locator)
				.orElseThrow(() -> new NotFountException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND"));
		try {

			reservationRepo.deleteByLocator(locator);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		
		return "LOCATOR_DELETED";
	}

}
