package br.com.leofalves.coursemongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.leofalves.coursemongo.domain.User;
import br.com.leofalves.coursemongo.dto.UserDTO;
import br.com.leofalves.coursemongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User usr = service.findById(id);
		//UserDTO usrDto = new UserDTO(usr);
		//return ResponseEntity.ok().body(usrDto);
		return ResponseEntity.ok().body(new UserDTO(usr));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> findById(@RequestBody UserDTO usrDto){
		User usr = service.insert(service.fromDTO(usrDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usr.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
