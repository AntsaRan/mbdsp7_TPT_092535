package com.tpt.api_mbds.repository;

import com.tpt.api_mbds.model.Equipe;
import com.tpt.api_mbds.model.Jeton;
import com.tpt.api_mbds.model.Points;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.*;
import java.util.List;

public interface PointsRepository extends MongoRepository<Points, String>  {
    List<Points> findByAdresseContaining(String adresse);
}
