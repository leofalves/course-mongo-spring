package br.com.leofalves.coursemongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leofalves.coursemongo.domain.User;
import br.com.leofalves.coursemongo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
}
