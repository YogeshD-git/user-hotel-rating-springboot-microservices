package com.microservice.user.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.user.entities.User;
import com.microservice.user.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/user-service")

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{		
		User userToSave = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userToSave);
	}
	int retryCount = 1;
	@GetMapping("/{userId}")
	//@CircuitBreaker(name="HotelRatingBreaker",fallbackMethod = "hotelRatingFallback")
	//@Retry(name="HotelRatingRetry",fallbackMethod = "hotelRatingFallback")
	@RateLimiter(name="userRateLimiter",fallbackMethod = "hotelRatingFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		System.out.println("--------retry count-------"+retryCount);
		retryCount++;
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllser()
	{
		List<User> users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}
	
	//falllback method for circuit breaker
	public ResponseEntity<User> hotelRatingFallback(String userID,Exception ex)
	{
		System.out.println("fallback is executed!!"+ex.getMessage());
		User user = User.builder().email("dummy@gmail.com").
				name("fs fs").
				about("dummy record created, some service is down").
				userId("9898").
				build();
		return new ResponseEntity(user,HttpStatus.OK);
	}

}
