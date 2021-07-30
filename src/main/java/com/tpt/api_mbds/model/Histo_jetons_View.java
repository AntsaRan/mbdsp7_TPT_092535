package com.tpt.api_mbds.model;

import java.util.Date;

public class Histo_jetons_View {
    private int id;
    private int idUtilisateur;
    private String idTransaction;
    private Date dateTransaction;
    private int montant;
    private int idPari;

    public Histo_jetons_View() {
    }

    public Histo_jetons_View(int id, int idUtilisateur, String idTransaction, Date dateTransaction, int montant, int idPari) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idTransaction = idTransaction;
        this.dateTransaction = dateTransaction;
        this.montant = montant;
        this.idPari = idPari;
    }

    public Histo_jetons_View(int idUtilisateur, String idTransaction, Date dateTransaction, int montant, int idPari) {
        this.idUtilisateur = idUtilisateur;
        this.idTransaction = idTransaction;
        this.dateTransaction = dateTransaction;
        this.montant = montant;
        this.idPari = idPari;
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

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
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
}
