package com.microservice.hotel.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.hotel.entities.Hotel;
import com.microservice.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		String hotelId = UUID.randomUUID().toString();
		hotel.setId(hotelId);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));		
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
	}
	
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels()
	{
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotel());

	}

}
