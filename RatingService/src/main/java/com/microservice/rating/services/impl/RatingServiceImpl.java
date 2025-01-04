package com.microservice.rating.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.rating.entities.Rating;
import com.microservice.rating.repositories.RatingRepository;
import com.microservice.rating.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userID) {
		// TODO Auto-generated method stub
		return ratingRepository.findByUserId(userID);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelID) {
		// TODO Auto-generated method stub
		return ratingRepository.findByHotelId(hotelID);
	}

}
