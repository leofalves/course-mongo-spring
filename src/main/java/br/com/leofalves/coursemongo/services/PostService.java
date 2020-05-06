package br.com.leofalves.coursemongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leofalves.coursemongo.domain.Post;
import br.com.leofalves.coursemongo.repositories.PostRepository;
import br.com.leofalves.coursemongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	PostRepository repo;
		
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		//return repo.findByTitleContainingIgnoreCase(text);
		
		/*
		 * Testando o @Query
		 * */
		return repo.findByQueryMongo(text);
	}
}
