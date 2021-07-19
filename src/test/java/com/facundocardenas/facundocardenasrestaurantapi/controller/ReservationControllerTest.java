package com.facundocardenas.facundocardenasrestaurantapi.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import restaurant.api.controllers.ReservationController;
import restaurant.api.exceptions.AppException;
import restaurant.api.jsons.CreateReservationRest;
import restaurant.api.jsons.ReservationRest;
import restaurant.api.responses.AppResponse;
import restaurant.api.services.ReservationService;

public class ReservationControllerTest {

	
	private CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
	private static final String SUCCES_STATUS = "Succes";
	private static final String SUCCES_CODE = "200 OK";
	private static final String OK = "OK";
	
	private static final Long RESERVATION_ID=1L;
	private static final Long RESTAURANT_ID=1L;
	
	private static final Date DATE=new Date();
	
	private static final Long PERSON=1L;
	
	private static final Long TURN_ID=1L;
	
	private static final String LOCATOR = "Burge 22";
	private static final ReservationRest RRest = new ReservationRest();
	
	@Mock // Servicio a Mockear.
	ReservationService reserService;

	@InjectMocks

	ReservationController reserController;

	@Before
	public void init() throws AppException {

		MockitoAnnotations.initMocks(this);
		
		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setPerson(PERSON);
		CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN_ID);
		
		
		//Mockeo (Simulo el servicio) para que se utilice con los datos que acabo de Setear.
		Mockito.when(reserService.createReservation(CREATE_RESERVATION_REST)).thenReturn(LOCATOR);
		
		
		//Vahacia el otro Test que devuelve Reserva segun el ID que pasen por parametro.
		Mockito.when(reserService.getReservationById(RESERVATION_ID)).thenReturn(RRest);
	}
	
	@Test
	public void CreateReservationRestTest() throws AppException{
		
		
        final AppResponse<String> response = reserController.createReservation(CREATE_RESERVATION_REST);
		
		assertEquals(response.getStatus(), SUCCES_STATUS );
		assertEquals(response.getCode(), SUCCES_CODE );
		assertEquals(response.getMessage(), OK );
		assertEquals(response.getData(), LOCATOR );
	}
	@Test
	public void getReservationByIdTest() throws AppException{
		
		final AppResponse<ReservationRest> response = reserController.getReservationById(RESERVATION_ID);
		
		assertEquals(response.getStatus(), SUCCES_STATUS );
		assertEquals(response.getCode(), SUCCES_CODE );
		assertEquals(response.getMessage(), OK );
		assertEquals(response.getData(), RRest );
	}	
		
	
	
}
