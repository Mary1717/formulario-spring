package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.repositories.UserRepository;

/**
 * @author MaryL
 *
 */
@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	//Buscar todos
	@GetMapping("")
	public List<User> findAll() {
		return userRepository.findAll();
	}

	//buscar por id
	@GetMapping("{documentNumber}")
	public User buscarporId(@PathVariable Long documentNumber) {
		return userRepository.findById(documentNumber).orElse(null);
	}

	//crear
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("")
	public User createUser(@RequestBody User user) {
		if(user!=null) {
			user.setPassword(encryptPassword(user.getPassword()));
			return userRepository.save(user);
		}
		return null;
	}

	//actualizar
	@PutMapping("{documentNumber}")
	public User updateUser(@RequestBody User newUser,@PathVariable Long documentNumber) {
		User user=userRepository.findById(documentNumber).orElse(null);
		if(user!=null && newUser!=null) {
			user.setUser(newUser.getUser());
			user.setPassword(encryptPassword(user.getPassword()));
			return userRepository.save(user);
		}
		return null;
	}
	
	//eliminar
	@DeleteMapping("{documentNumber}")
	public boolean deleteById(@PathVariable Long documentNumber) {
		User user=userRepository.findById(documentNumber).orElse(null);
		if(user!=null) {
			userRepository.deleteById(documentNumber);
			return true;
		}
		return false;
	}
	public String encryptPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
	
	public static void main(String[] args) {
		System.out.println("PASSWORD: " + new BCryptPasswordEncoder().encode("administrador"));
	}
}
