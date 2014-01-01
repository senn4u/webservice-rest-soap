package com.repository;

import com.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Ben Yasin
 */
public interface UserRepository extends MongoRepository<User,ObjectId>{


}
