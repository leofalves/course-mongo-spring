package br.com.leofalves.coursemongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.leofalves.coursemongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	List<Post> findByTitle(String text);
	
	List<Post> findByTitleContaining(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	
	/*
	 * Retirado de: https://docs.mongodb.com/manual/reference/operator/query/regex/
	 * { <field>: { $regex: /pattern/, $options: '<options>' } }
	 * ?0 primeiro parametro do método
	 * options: i = Case insensitive
	 * */
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> findByQueryMongo (String text);

}
