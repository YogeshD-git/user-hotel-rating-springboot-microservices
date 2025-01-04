package com.microservice.user.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="microservice_users")
public class User {
	
	@Id()
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name",length = 20)
	private String name;
	
	@Column(name="user_email")
	private String email;
	
	@Column(name="user_about",length = 300)
	private String about;
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();
		

}
