package com.facundocardenas.facundocardenasrestaurantapi.services;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Array;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import restaurant.api.entities.Board;
import restaurant.api.entities.Reservation;
import restaurant.api.entities.Restaurant;
import restaurant.api.entities.Turn;
import restaurant.api.exceptions.AppException;
import restaurant.api.repository.RestaurantRepository;
import restaurant.api.services.impl.RestaurantServiceImpl;

public class RestaurantServiceTest {

	private static final Long RESTAURANT_ID = 1L;
	private static final String NAME = "BurgerKill";
	private static final String DESCRIPTION = "Burgers que te asesinan del sabor";
	private static final String ADDRES = "Av Warner 4455";
	private static final String IMAGE = "www.google.com";

	public static final Restaurant RESTAURANT = new Restaurant();
	public static final List<Restaurant> RESTAURANT_LIST = new ArrayList<>();
	public static final List<Turn> TURN_LIST = new ArrayList<>();
	public static final List<Board> BOARD_LIST = new ArrayList<>();
	public static final List<Reservation> RESERVATION_LIST = new ArrayList<>();

	@Mock
	RestaurantRepository repoR;

	@InjectMocks
	RestaurantServiceImpl rService;

	@Before
	public void init() throws AppException {

		MockitoAnnotations.initMocks(this);

		RESTAURANT.setName(NAME);
		RESTAURANT.setDescription(DESCRIPTION);
		RESTAURANT.setAddres(ADDRES);
		RESTAURANT.setId(RESTAURANT_ID);
		RESTAURANT.setImage(IMAGE);
		RESTAURANT.setTurns(TURN_LIST);
		RESTAURANT.setBoards(BOARD_LIST);
		RESTAURANT.setReservations(RESERVATION_LIST);
	}

	@Test
	public void getRestaurantByIdTest() throws AppException {

		Mockito.when(repoR.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));

		rService.getRestaurantById(RESTAURANT_ID);
	}

	

	@Test(expected = AppException.class)
	public void getRestaurantByIdFailTest() throws AppException {
		// Vacio para que falle pero para que nos deje Pasar el ServiceImp donde mapea
		// las Entidades.
		Mockito.when(repoR.findById(RESTAURANT_ID)).thenReturn(Optional.empty());
		rService.getRestaurantById(RESTAURANT_ID);

		fail(); // En coverage queda en Rojo porque falla al ser un test que compruebe la falla
				// o el bug
	}

	/*@Test(expected = AppException.class)
	public void getRestaurantsFailTest() throws AppException {
		// Vacio para que falle pero para que nos deje Pasar el ServiceImp donde mapea
		// las Entidades.
		Mockito.when(repoR.findRestaurants()).thenReturn(RESTAURANT_LIST);

		rService.getRestaurants();

		fail(); // En coverage queda en Rojo porque falla al ser un test que compruebe la falla
				// o el bug
	}*/
}
