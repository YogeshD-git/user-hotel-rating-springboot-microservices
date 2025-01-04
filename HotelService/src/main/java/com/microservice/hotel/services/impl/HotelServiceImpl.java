package com.microservice.hotel.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.hotel.entities.Hotel;
import com.microservice.hotel.exception.ResourceNotFoundException;
import com.microservice.hotel.repositories.HotelRepository;
import com.microservice.hotel.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		
		return hotelRepository.findAll();
	}

	@Override
	public Hotel get(String id) {
		
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given Id is not present!"));
	}

}
