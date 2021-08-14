package com.tpt.api_mbds.model;

import java.util.Date;

public class HistoUserName {
    private int id;
    private int idUtilisateur;
    private String nom;
    private String nomTransaction;
    private int idtransaction;
    private Date datetransaction;
    private int montant;

    public HistoUserName() {
    }

    public HistoUserName(int id, int idUtilisateur, String nom, String nomTransaction, int idtransaction, Date datetransaction, int montant) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.nomTransaction = nomTransaction;
        this.idtransaction = idtransaction;
        this.datetransaction = datetransaction;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(int idtransaction) {
        this.idtransaction = idtransaction;
    }

    public Date getDatetransaction() {
        return datetransaction;
    }

    public void setDatetransaction(Date datetransaction) {
        this.datetransaction = datetransaction;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getNomTransaction() {
        return nomTransaction;
    }

    public void setNomTransaction(String nomTransaction) {
        this.nomTransaction = nomTransaction;
    }
}
