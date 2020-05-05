package br.com.leofalves.coursemongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.leofalves.coursemongo.domain.Post;
import br.com.leofalves.coursemongo.domain.User;
import br.com.leofalves.coursemongo.dto.AuthorDTO;
import br.com.leofalves.coursemongo.repositories.PostRepository;
import br.com.leofalves.coursemongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, Instant.now(), "Partiu Viagem", "Vou para São Paulo", new AuthorDTO(maria));
		Post post2 = new Post(null, Instant.parse("2020-04-15T18:35:24.00Z"), "Bom dia", "Acordei Feliz Hoje", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
	}

}
