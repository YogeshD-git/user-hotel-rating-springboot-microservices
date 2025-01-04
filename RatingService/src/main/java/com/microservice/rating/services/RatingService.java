package com.microservice.rating.services;

import java.util.List;

import com.microservice.rating.entities.Rating;

public interface RatingService {
	
	//create
	Rating create(Rating rating);
	
	//get all ratings
	List<Rating> getAllRatings();
	
	//get All by userId
	List<Rating> getRatingByUserId(String userID);
	
	//get all rating by hodetID
	List<Rating> getRatingByHotelId(String hotelID);
	
	

}
