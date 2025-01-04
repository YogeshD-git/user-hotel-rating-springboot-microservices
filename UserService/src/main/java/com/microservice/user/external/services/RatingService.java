package com.microservice.user.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.microservice.user.entities.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	@PostMapping("/ratings")
	public Rating createRating(Rating values);

}
