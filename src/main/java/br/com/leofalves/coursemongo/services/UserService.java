package br.com.leofalves.coursemongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leofalves.coursemongo.domain.User;
import br.com.leofalves.coursemongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		Optional<User> newObj = repo.findById(obj.getId());
		updateData(newObj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")), obj);
		repo.save(newObj.get());
		return newObj.get();
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
