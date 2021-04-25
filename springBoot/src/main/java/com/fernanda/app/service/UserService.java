package com.fernanda.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fernanda.app.entity.User;

public interface UserService {
	
	    // Para traer todos los usuarios de forma de un iterable
		public Iterable<User> findAll();
		// Hacer paginacion a los usuarios que se traiga
		public Page<User> findAll(Pageable pageable);
		//Devuelve un optional, busca usuarios por id
		public Optional<User> findById(long id);
		// Guardar los usuarios
		public User save(User user);
	     //Eliminar usuarios por Id
		public void deleteById(Long id);
		
}
