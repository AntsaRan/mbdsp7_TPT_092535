package com.tpt.api_mbds.repository;

import com.tpt.api_mbds.model.Equipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EquipeRepository extends MongoRepository<Equipe, String> {
    List<Equipe> findByNomContaining(String nom);
}
