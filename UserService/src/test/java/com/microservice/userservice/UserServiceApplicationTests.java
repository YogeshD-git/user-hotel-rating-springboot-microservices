package com.microservice.userservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import com.microservice.user.UserServiceApplication;
import com.microservice.user.entities.Rating;
import com.microservice.user.external.services.RatingService;



@SpringBootTest(classes = UserServiceApplication.class) 
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	  private RatingService ratingService;
	
	@Test
	void createRating()
	{
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("test").build();

		Rating saved = ratingService.createRating(rating);
		
		System.out.println("testttttttttttttttt tetstttstst");
	}
	

}
