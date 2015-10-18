package com.cassianoalves.quotes.repository;

import com.cassianoalves.quotes.model.UserConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserConfirmationRepository extends MongoRepository<UserConfirmation, String> {}
