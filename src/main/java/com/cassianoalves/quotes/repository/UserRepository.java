package com.cassianoalves.quotes.repository;

import com.cassianoalves.quotes.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
