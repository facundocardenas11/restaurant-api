package restaurant.api.services;

import org.springframework.stereotype.Service;

import restaurant.api.exceptions.AppException;
import restaurant.api.jsons.CreateReservationRest;
import restaurant.api.jsons.ReservationRest;

@Service
public interface ReservationService {

	ReservationRest getReservationById(Long reservationId) throws AppException;

	String createReservation(CreateReservationRest createR) throws AppException;
}
