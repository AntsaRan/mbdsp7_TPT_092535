package com.tpt.api_mbds.repository;

import com.tpt.api_mbds.model.Jeton;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JetonRepository extends MongoRepository<Jeton, String> {
}
