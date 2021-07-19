package com.facundocardenas.facundocardenasrestaurantapi.controller;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import restaurant.api.controllers.RestaurantController;
import restaurant.api.exceptions.AppException;
import restaurant.api.jsons.RestaurantRest;
import restaurant.api.jsons.TurnRest;
import restaurant.api.responses.AppResponse;
import restaurant.api.services.RestaurantService;

public class RestaurantControllerTest {

	private static final String NAME="BurgerKill";
	private static final String DESCRIPTION="Burgers que te asesinan del sabor";
	private static final String ADDRES="Av Warner 4455";
	private static final String IMAGE="www.google.com";
	private static final Long RESTAURANT_ID=1L;
	private static final String SUCCES_STATUS = "Succes";
	private static final String SUCCES_CODE = "200 OK";
	private static final String OK = "OK";
	
	public static final List<TurnRest> TURN_LIST = new ArrayList<>();
	public static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
	public static final List<RestaurantRest> RESTAURANT_LIST = new ArrayList<>();;
	//El mockear los test sirve para simular que utilizan lo que lo sservicios y controladores realmente utilizan.
	
	@Mock
	private RestaurantService rService;
	
	
	@InjectMocks
	RestaurantController restaurantController;
	
	
	//Before para el Set de Valores 
	@Before
	public void init() throws AppException{
		
		MockitoAnnotations.initMocks(this);
		
		RESTAURANT_REST.setName(NAME);
		RESTAURANT_REST.setDescription(DESCRIPTION);
		RESTAURANT_REST.setAddres(ADDRES);
		RESTAURANT_REST.setId(RESTAURANT_ID);
		RESTAURANT_REST.setImage(IMAGE);
		RESTAURANT_REST.setTurns(TURN_LIST);
		
		//Mockea el servicio y hace que utilice las demas clases asi funciona el test y retorna lo Seteado
		//Utiliza el RestaurantServiceImpl, al mockearse puede usar el metodo del service implementando y en el test el controller
		
		Mockito.when(rService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);
	}
	
	
	//Ejecuto el Test con los datos de RestaurantRest seteado
	@Test
	public void getRestaurantByIdTest() throws AppException{
		
		final AppResponse<RestaurantRest> response = restaurantController.getRestaurantById(RESTAURANT_ID);
		
		assertEquals(response.getStatus(), SUCCES_STATUS );
		assertEquals(response.getCode(), SUCCES_CODE );
		assertEquals(response.getMessage(), OK );
		assertEquals(response.getData(), RESTAURANT_REST );
	}	
		
	
	@Test
	public void getRestaurantsTest() throws AppException{
	
		final AppResponse<List<RestaurantRest>> response = restaurantController.getRestaurants();
		
		assertEquals(response.getStatus(), SUCCES_STATUS );
		assertEquals(response.getCode(), SUCCES_CODE );
		assertEquals(response.getMessage(), OK );
		assertEquals(response.getData(), RESTAURANT_LIST );
	}
	
}

