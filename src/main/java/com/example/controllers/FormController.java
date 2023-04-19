package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.models.FavoritePcMark;
import com.example.models.Form;
import com.example.models.User;
import com.example.repositories.FavoritePcMarkRepository;
import com.example.repositories.FormRepository;
import com.example.repositories.UserRepository;

/**
 * @author MaryL
 *
 */
@RestController
@RequestMapping("api/form")
@CrossOrigin("*")
public class FormController {

	@Autowired
	private FormRepository formRepository;
	@Autowired
	private FavoritePcMarkRepository favoritePcMarkRepository;
	@Autowired
	private UserRepository userRepository;

	//Buscar todos
	@GetMapping("")
	public List<Form>findAll(){
		return formRepository.findAll();
	}

	//buscar por id
	@GetMapping("{idForm}")
	public Form findById(@PathVariable int idForm) {
		return formRepository.findById(idForm).orElse(null);
	}

	//crear
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("user/{documentNumber}/mark/{idMark}")
	public Form createForm(@RequestBody Form form, @PathVariable Long documentNumber,@PathVariable int idMark) {
		User user = userRepository.findById(documentNumber).orElse(null);
		FavoritePcMark favoritePcMark = favoritePcMarkRepository.findById(idMark).orElse(null);
		if(form!=null && user!=null && favoritePcMark!=null) {
			form.setUser(user);
			form.setFavoritePcMark(favoritePcMark);
			return formRepository.save(form);
		}
		return null;
	}

	//actualizar
	@PutMapping("{idForm}/user/{documentNumber}/mark/{idMark}")
	public Form updateForm(@RequestBody Form newForm, @PathVariable int idForm,@PathVariable Long documentNumber,@PathVariable int idMark) {
		User user = userRepository.findById(documentNumber).orElse(null);
		FavoritePcMark favoritePcMark = favoritePcMarkRepository.findById(idMark).orElse(null);
		Form form = formRepository.findById(idForm).orElse(null);
		if(form!=null && newForm!=null && user!=null && favoritePcMark!=null) {
			form.setUser(user);
			form.setEmail(newForm.getEmail());
			form.setComments(newForm.getComments());
			form.setFavoritePcMark(favoritePcMark);
			form.setResponseDate(newForm.getResponseDate());

			return formRepository.save(form);
		}
		return null;
	}

	//Eliminar
	@DeleteMapping("{idForm}")
	public boolean eliminarPorId( @PathVariable int idForm) {
		Form form = formRepository.findById(idForm).orElse(null);
		if(form!=null) {
			formRepository.deleteById(idForm);
			return true;
		}
		return false;
	}
}
