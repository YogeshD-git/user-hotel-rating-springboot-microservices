package com.microservice.apigateway.models;

import java.util.Collection;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String userID;
	private String accessToken;
	private String refreshToken;
	private long expireAt;
	private Collection<String> authorities;
	
}
