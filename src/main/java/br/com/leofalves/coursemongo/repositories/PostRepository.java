package br.com.leofalves.coursemongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.leofalves.coursemongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
