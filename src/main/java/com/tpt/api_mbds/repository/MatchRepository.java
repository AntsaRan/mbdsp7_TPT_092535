package com.tpt.api_mbds.repository;

import com.tpt.api_mbds.model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface MatchRepository extends MongoRepository<Match, String> {
    List<Match> findMatchByEtatContaining(String etat);
    List<Match> findMatchByEquipe1_Id(String id);
    List<Match> findMatchByEquipe2_Id(String id);
    List<Match> findMatchByDateEquals(Date date);
    List<Match> findMatchesByDate(Date date);
    List<Match> findMatchByDateContaining(Date date);
    List<Match> findMatchesByDateBetween(Date date1,Date date2);
    List<Match> findMatchesByDateBetweenAndEtatIs(Date date1,Date date2,String etat);
    List<Match> findMatchesByDateBefore(Date date1,Date date2);
    List<Match> findAllByDateOrderByDateAsc();
    List<Match> findMatchesByEtatContainingOrderByDateAsc(String etat);
    Match findByIdAndEtat(String id,String etat);

}
