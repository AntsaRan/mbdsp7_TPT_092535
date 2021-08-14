package com.tpt.api_mbds.model;

import com.tpt.api_mbds.repository.MatchRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Document(collection="match")
public class Match {
    @Autowired
    MatchRepository matchRepository;

    @Id
    private String id;

    @DBRef
    private  Equipe equipe1;
    @DBRef
    private  Equipe equipe2;

    //private List<Equipe> equipes;

    private Date date;
    private String lieu;
    private String etat;
    private Integer scoreEquipe1;
    private Integer scoreEquipe2;
    private Integer cornerEquipe1;
    private Integer cornerEquipe2;
    private Integer possessionEquipe1;
    private Integer possessionEquipe2;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Integer getScoreEquipe1() {
        return scoreEquipe1;
    }

    public void setScoreEquipe1(Integer scoreEquipe1) {
        this.scoreEquipe1 = scoreEquipe1;
    }

    public Integer getScoreEquipe2() {
        return scoreEquipe2;
    }

    public void setScoreEquipe2(Integer scoreEquipe2) {
        this.scoreEquipe2 = scoreEquipe2;
    }

    public Integer getCornerEquipe1() {
        return cornerEquipe1;
    }

    public void setCornerEquipe1(Integer cornerEquipe1) {
        this.cornerEquipe1 = cornerEquipe1;
    }

    public Integer getCornerEquipe2() {
        return cornerEquipe2;
    }

    public void setCornerEquipe2(Integer cornerEquipe2) {
        this.cornerEquipe2 = cornerEquipe2;
    }

    public Integer getPossessionEquipe1() {
        return possessionEquipe1;
    }

    public void setPossessionEquipe1(Integer possessionEquipe1) {
        this.possessionEquipe1 = possessionEquipe1;
    }

    public Integer getPossessionEquipe2() {
        return possessionEquipe2;
    }

    public void setPossessionEquipe2(Integer possessionEquipe2) {
        this.possessionEquipe2 = possessionEquipe2;
    }

    public Match() {

    }

   



    public Match(String id, Equipe equipe1, Equipe equipe2, Date date, String lieu, String etat, Integer scoreEquipe1, Integer scoreEquipe2, Integer cornerEquipe1, Integer cornerEquipe2, Integer possessionEquipe1, Integer possessionEquipe2) {
        this.id = id;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date = date;
        this.lieu = lieu;
        this.etat = etat;
        this.scoreEquipe1 = scoreEquipe1;
        this.scoreEquipe2 = scoreEquipe2;
        this.cornerEquipe1 = cornerEquipe1;
        this.cornerEquipe2 = cornerEquipe2;
        this.possessionEquipe1 = possessionEquipe1;
        this.possessionEquipe2 = possessionEquipe2;
    }

    public Match(Equipe equipe1, Equipe equipe2, Date date, String lieu, String etat, Integer scoreEquipe1, Integer scoreEquipe2, Integer cornerEquipe1, Integer cornerEquipe2, Integer possessionEquipe1, Integer possessionEquipe2) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date = date;
        this.lieu = lieu;
        this.etat = etat;
        this.scoreEquipe1 = scoreEquipe1;
        this.scoreEquipe2 = scoreEquipe2;
        this.cornerEquipe1 = cornerEquipe1;
        this.cornerEquipe2 = cornerEquipe2;
        this.possessionEquipe1 = possessionEquipe1;
        this.possessionEquipe2 = possessionEquipe2;
    }

    public void startmatch() {
        this.setEtat("2");
    }

    public void endmatch(Match m,MatchRepository repository) {
        m.setEtat("3");
        m.setscores();
        this.matchRepository=repository;

        Match _match = m;
        //System.out.println("ITO ILAY MATCH ID "+_match.getId());
        _match.setEquipe1(m.getEquipe1());
        //System.out.println("ITO ILAY MATCH EQUIPE1 "+_match.getEquipe1().getId());
        _match.setEquipe2(m.getEquipe2());
        _match.setDate(m.getDate());
        _match.setEtat(m.getEtat());
        // System.out.println("ITO ILAY MATCH ETAT "+_match.getEtat());
        _match.setLieu(m.getLieu());
        _match.setScoreEquipe1(m.getScoreEquipe1());
        _match.setScoreEquipe2(m.getScoreEquipe2());
        _match.setCornerEquipe1(m.getCornerEquipe1());
        _match.setCornerEquipe2(m.getCornerEquipe2());
        _match.setPossessionEquipe1(m.getPossessionEquipe1());
        _match.setPossessionEquipe2(m.getPossessionEquipe2());
        matchRepository.save(_match);


    }

    public void setscores() {
        Random random = new Random();
        int randomNum = ThreadLocalRandom.current().nextInt(30, 100 + 1);
        this.setScoreEquipe1( random.nextInt(8));
        this.setScoreEquipe2( random.nextInt(8));
        this.setCornerEquipe1( random.nextInt(8));
        this.setCornerEquipe2( random.nextInt(8));
        this.setPossessionEquipe1( random.nextInt(randomNum));
        this.setPossessionEquipe2( 100-this.getPossessionEquipe1());

    }

}
