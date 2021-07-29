package com.tpt.api_mbds.model;

import java.util.Date;

public class Historique_Jetons {
    private int id;
    private int idUtilisateur;
    private int idTransaction;
    private Date dateTransaction;
    private int montant;
    private int idPari;

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

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getIdPari() {
        return idPari;
    }

    public void setIdPari(int idPari) {
        this.idPari = idPari;
    }

    public Historique_Jetons() {
    }

    public Historique_Jetons(int id, int idUtilisateur, int idTransaction, Date dateTransaction, int montant, int idPari) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idTransaction = idTransaction;
        this.dateTransaction = dateTransaction;
        this.montant = montant;
        this.idPari = idPari;
    }

    public Historique_Jetons(int idUtilisateur, int idTransaction, int montant, int idPari) {
        this.idUtilisateur = idUtilisateur;
        this.idTransaction = idTransaction;
        this.montant = montant;
        this.idPari = idPari;
    }
}
