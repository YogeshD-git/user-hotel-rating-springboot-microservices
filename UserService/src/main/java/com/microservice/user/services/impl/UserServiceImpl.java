package com.microservice.user.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.user.Repositories.UserRepository;
import com.microservice.user.entities.Hotel;
import com.microservice.user.entities.Rating;
import com.microservice.user.entities.User;
import com.microservice.user.exceptions.ResourceNotFoundException;
import com.microservice.user.external.services.HotelService;
import com.microservice.user.services.UserService;

import ch.qos.logback.classic.Logger;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	

	@Override
	public User saveUser(User user) {
		//generate unique user ID
		String randomUserID = UUID.randomUUID().toString();
		user.setUserId(randomUserID);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {

			 // Fetch all users from the database using the user repository
		    List<User> users = userRepository.findAll();

		    // Loop through each user to get their ratings and hotel details
		    users.forEach(user -> {
		        // Step 1: Get all ratings for the current user by making an API call to the rating service
		        // Example URL: http://localhost:8083/ratings/user/{userId}
		        Rating[] ratings = restTemplate.getForObject("http://Rating-Service/ratings/user/" + user.getUserId(), Rating[].class);
		       

		        // Step 2: Convert the ratings array to a list for easier manipulation
		        List<Rating> ratingsList = Arrays.asList(ratings);

		        // Step 3: For each rating, get the hotel information
		        ratingsList = ratingsList.stream().map(rating -> {
		            // Make an API call to get the hotel by its hotelId
		            // Example URL: http://localhost:8082/hotels/{hotelId}
		            ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("http://Hotel-Service/hotels/" + rating.getHotelId(), Hotel.class);
		            
		            // Get the hotel object from the API response
		            Hotel hotel = responseEntity.getBody();
		            
		            // Set the fetched hotel to the rating object
		            rating.setHotel(hotel);

		            // Return the updated rating
		            return rating;
		        }).collect(Collectors.toList());

		        // Step 4: Assign the updated ratings list to the current user
		        user.setRatings(ratingsList);
		    });

		    // Return the list of all users, now with their ratings and hotel details
		    return users;
	}

	@Override
	public User getUser(String userId) {
		//get user from database with the help of user repository
		User user =  userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given ID is not found on server!" + userId));
		//fetch ratings from rating service
		//http://192.168.45.54:8083/ratings/user/cd03ced0-41a5-45b5-a579-4ec995a94227
		
		Rating[] ratings= restTemplate.getForObject("http://Rating-Service/ratings/user/"+user.getUserId(), Rating[].class);
		
		//logger.info("{} ",ratings);
		
		List<Rating> ratingsList = Arrays.stream(ratings).toList();
		 ratingsList = 
				 ratingsList.stream().map(rating -> {
					 
					//api call to hotel service to get the hotel
					//http://localhost:8082/hotels/516fd991-775f-4eed-9418-a9a8a74e17aa
					// ResponseEntity<Hotel> forEntity = 
					 //restTemplate.getForEntity("http://Hotel-Service/hotels/"+rating.getHotelId(), Hotel.class);
					 
					Hotel hotel = hotelService.getHotel(rating.getHotelId());
					
					//logger.info("responce status code: {}",forEntity.getStatusCode());
					
					//set the hotel to rating
					rating.setHotel(hotel);
					
					return rating;
					
				}).collect(Collectors.toList());
		
		user.setRatings(ratingsList);
		
		return user;
		
		
	}

	@Override
	public void deleteUser(String userId) {
		
		
	}

	@Override
	public User updateUser(String userId) {
		User user = userRepository
				.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User with given ID is not found on server!" + userId));
		return user;
	
	}

}
