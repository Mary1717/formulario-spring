package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.FavoritePcMark;
import com.example.repositories.FavoritePcMarkRepository;

/**
 * @author MaryL
 *
 */
@RestController
@RequestMapping("api/favoritePc")
@CrossOrigin("*")
public class FavoritePcMarkController {

	@Autowired
	private FavoritePcMarkRepository favoritePcMarkRepository;

	//Buscar todos
	@GetMapping("")
	public List<FavoritePcMark>findAll(){
		return favoritePcMarkRepository.findAll();
	}

	//Buscar por id
	@GetMapping("{idMark}")
	public FavoritePcMark findById(@PathVariable int idMark) {
		return favoritePcMarkRepository.findById(idMark).orElse(null);
	}

}
