package br.com.leofalves.coursemongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leofalves.coursemongo.domain.User;
import br.com.leofalves.coursemongo.repositories.UserRepository;
import br.com.leofalves.coursemongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> usr = repo.findById(id);
		return usr.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
