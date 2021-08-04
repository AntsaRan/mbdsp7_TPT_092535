package com.example.bet.modele;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class HistoriqueJetons implements Parcelable {
    @SerializedName("id")
    private  String id;
    @SerializedName("idUtilisateur")
    private  String idUtilisateur;
    @SerializedName("idTransaction")
    private  String idTransaction;
    @SerializedName("dateTransaction")
    private  String dateTransaction;
    @SerializedName("montant")
    private  int montant;
    @SerializedName("idPari")
    private  String idPari;

    protected HistoriqueJetons(Parcel in) {
        id = in.readString();
        idUtilisateur = in.readString();
        idTransaction = in.readString();
        dateTransaction = in.readString();
        montant = in.readInt();
        idPari = in.readString();
    }

    public static final Creator<HistoriqueJetons> CREATOR = new Creator<HistoriqueJetons>() {
        @Override
        public HistoriqueJetons createFromParcel(Parcel in) {
            return new HistoriqueJetons(in);
        }

        @Override
        public HistoriqueJetons[] newArray(int size) {
            return new HistoriqueJetons[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getIdPari() {
        return idPari;
    }

    public void setIdPari(String idPari) {
        this.idPari = idPari;
    }

    public HistoriqueJetons() {
    }

    public HistoriqueJetons(String id, String idUtilisateur, String idTransaction, String dateTransaction, int montant, String idPari) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.idTransaction = idTransaction;
        this.dateTransaction = dateTransaction;
        this.montant = montant;
        this.idPari = idPari;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(idUtilisateur);
        parcel.writeString(idTransaction);
        parcel.writeString(dateTransaction);
        parcel.writeInt(montant);
        parcel.writeString(idPari);
    }
}
