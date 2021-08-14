package com.tpt.api_mbds.model;

import com.tpt.api_mbds.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.TimerTask;

public class TimerExample  extends TimerTask {

    private String name;
    private Match m;
    @Autowired
    MatchRepository matchRepository;

    public TimerExample(String n, Match m, MatchRepository repository) {
        this.name = n;
        this.m = m;
        this.matchRepository=repository;
    }


    @Override
    public void run() {

        if ("start".equalsIgnoreCase(name)) {
           // System.out.println("match etat avant start " + this.m.getEtat() + " " + new Date()); // TODO Auto-generated catch block
            this.m.startmatch();
            //System.out.println("match etat start " + this.m.getEtat() + " " + new Date()); // TODO Auto-generated catch block

            //////////////////////Eto ilay Update match to EnCours//////////////
            Match _match = new Match();
            _match=this.m;
           //System.out.println("ITO ILAY MATCH ID "+_match.getId());
            _match.setEquipe1(this.m.getEquipe1());
            //System.out.println("ITO ILAY MATCH EQUIPE1 "+_match.getEquipe1().getId());
            _match.setEquipe2(this.m.getEquipe2());
            _match.setDate(this.m.getDate());
            _match.setEtat(this.m.getEtat());
           // System.out.println("ITO ILAY MATCH ETAT "+_match.getEtat());
            _match.setLieu(this.m.getLieu());
            _match.setScoreEquipe1(this.m.getScoreEquipe1());
            _match.setScoreEquipe2(this.m.getScoreEquipe2());
            _match.setCornerEquipe1(this.m.getCornerEquipe1());
            _match.setCornerEquipe2(this.m.getCornerEquipe2());
            _match.setPossessionEquipe1(this.m.getPossessionEquipe1());
            _match.setPossessionEquipe2(this.m.getPossessionEquipe2());
            matchRepository.save(_match);

            this.cancel();
        }
        if ("end".equalsIgnoreCase(name)) {
            System.out.println("match avant etat end " + this.m.getEtat() + " " + new Date()); // TODO Auto-generated catch block
            //this.m.endmatch();
            System.out.println("match  etat end " + this.m.getEtat() + " "
                    + Thread.currentThread().getName()
                    + " "
                    + name
                    + " the task1 has executed successfully "
                    + new Date()
                    + " " + this.m.getScoreEquipe1() + " " + this.m.getScoreEquipe2()
            ); // TODO Auto-generated catch block

            //////////////////////Eto ilay Update match to EnCours//////////////
            Match _match = this.m;
            _match.setEquipe1(this.m.getEquipe1());
            _match.setEquipe2(this.m.getEquipe2());
            _match.setDate(this.m.getDate());
            _match.setEtat(this.m.getEtat());
            _match.setLieu(this.m.getLieu());
            _match.setScoreEquipe1(this.m.getScoreEquipe1());
            _match.setScoreEquipe2(this.m.getScoreEquipe2());
            _match.setCornerEquipe1(this.m.getCornerEquipe1());
            _match.setCornerEquipe2(this.m.getCornerEquipe2());
            _match.setPossessionEquipe1(this.m.getPossessionEquipe1());
            _match.setPossessionEquipe2(this.m.getPossessionEquipe2());
            matchRepository.save(_match);
            this.cancel();
        }

    }

    public void task1() {
        System.out.println(Thread.currentThread().getName() + " " + name + " the task1 has executed successfully " + new Date());
    }

    public void task2() {
        System.out.println(Thread.currentThread().getName() + " " + name + " the task2 kiki has executed successfully " + new Date());
    }

}
