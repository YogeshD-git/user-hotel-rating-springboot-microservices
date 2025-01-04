package com.microservice.hotel.services;

import java.util.List;

import com.microservice.hotel.entities.Hotel;

public interface HotelService {
	//create
	Hotel createHotel(Hotel hotel);
	
	//get all 
	List<Hotel> getAllHotel();
	
	//get hotel by hotel Id
	Hotel get(String id);
	
}
