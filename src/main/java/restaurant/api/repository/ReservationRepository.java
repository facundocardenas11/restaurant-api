package restaurant.api.repository;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import restaurant.api.entities.Reservation;
import restaurant.api.jsons.ReservationRest;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	ReservationRest getReservationfindById(Long reservationId); //Busca reserva por ID.

	Optional<Reservation> findByLocator(String locator); //Busca reserva por Locator.
	
	
	@Modifying
	@Transactional //aviso que hago una modificacion en la base de datos.
	
	Optional<Reservation> deleteByLocator(String locator);  //Borrar (cancelar) unaa reserva
	
	Optional<Reservation> findByTurnAndRestaurantId(String turn, Long restaurant_id);

	
}
