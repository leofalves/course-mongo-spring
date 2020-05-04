package br.com.leofalves.coursemongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.leofalves.coursemongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
