package com.cassianoalves.quotes.repository;

import com.cassianoalves.quotes.model.Invite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InviteRepository extends MongoRepository<Invite, String> {}
