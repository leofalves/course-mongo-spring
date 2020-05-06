package br.com.leofalves.coursemongo.repositories;

import java.util.Date;
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
	
	
	
	/*
	 * Buscar Posts que contenham o parametro text no title ou no corpo ou nos comentários e 
	 * que o post esteja entre as datas mínima e máxima
	 * 
	 * Operador AND 
	 * https://docs.mongodb.com/manual/reference/operator/query/and/
	 * { $and: [ { <expression1> }, { <expression2> } , ... , { <expressionN> } ] }
	 * 
	 * Operador maior ou igual
	 * https://docs.mongodb.com/manual/reference/operator/query/gte/
	 * {field: {$gte: value} }
	 * 
	 * 
	 * Operador menor ou igual
	 * https://docs.mongodb.com/manual/reference/operator/query/lte/
	 * 
	 * 
	 * Operador OR
	 * https://docs.mongodb.com/manual/reference/operator/query/or/
	 * { $or: [ { <expression1> }, { <expression2> }, ... , { <expressionN> } ] }
	 * */
	@Query("{ $and: [ {'date': {$gte: ?1} }, { 'date': { $lte: ?2} }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date dateMin, Date dateMax);
	

}
