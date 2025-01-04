package com.microservice.user.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException()
	{
		super("Resource not found on server!");
	}
	public ResourceNotFoundException(String ex)
	{
		super(ex);
	}
}
