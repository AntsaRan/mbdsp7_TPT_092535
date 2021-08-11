package com.tpt.api_mbds.repository;

import com.tpt.api_mbds.model.Jeton;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JetonRepository extends MongoRepository<Jeton, String> {
    Jeton findAllById(String id);
    Jeton findAllById(ObjectId id);
}
