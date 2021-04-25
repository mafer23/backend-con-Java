package com.fernanda.app.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernanda.app.entity.User;
import com.fernanda.app.service.UserService;
import java.util.List;





@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userServices;
	
	// Create a new user
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userServices.save(user));
	}
	
	// Read an user
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable(value="id") Long userId){
	   Optional<User> oUser = userServices.findById(userId);
	   
	   if(!oUser.isPresent()) {
		   return ResponseEntity.notFound().build();
	   }
	   
	   return ResponseEntity.ok(oUser);
	}
	
	// Update an User
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value ="id") Long userId){
		Optional<User> user =userServices.findById(userId);
	
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		user.get().setName(userDetails.getName());
		user.get().setSurname(userDetails.getSurname());
		user.get().setEmail(userDetails.getEmail());
		user.get().setEnabled(userDetails.getEnabled());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userServices.save(user.get()));
		
	}
	
	// Delete an User
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value ="id") Long userId){
		if(!userServices.findById(userId).isPresent()) {
			return ResponseEntity.notFound().build();
			
		}
		userServices.deleteById(userId);
		return ResponseEntity.ok().build();
		
		
	}
	
	// Read all Users
	@GetMapping
	public List<User> readAll(){
		
		List<User> users = StreamSupport
				.stream(userServices.findAll().spliterator(), false)
				.collect(Collectors.toList());
	
		return users;
	}
	
	
}
