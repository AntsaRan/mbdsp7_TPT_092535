package com.tpt.api_mbds.repository;

import com.tpt.api_mbds.model.Match_regle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Match_regleRepository extends MongoRepository<Match_regle, String> {

    Match_regle findMatch_regleByIdMatch(ObjectId idMatch);

}
