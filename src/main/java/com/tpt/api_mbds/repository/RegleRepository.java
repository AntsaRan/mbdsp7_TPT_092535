package com.tpt.api_mbds.repository;

import com.tpt.api_mbds.model.Regle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RegleRepository  extends MongoRepository<Regle, String> {
    List<Regle> findReglesByTitreContaining(String titre);
    List<Regle> findAllByOrderByOrdreAsc();
}
