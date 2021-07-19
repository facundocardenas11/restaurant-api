package restaurant.api.services;

import org.springframework.stereotype.Service;

import restaurant.api.exceptions.AppException;

@Service
public interface CancelReservationService {

	
	public String deleteReservation(String locator) throws AppException;


}
