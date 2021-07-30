package com.tpt.api_mbds.Controller;

import com.tpt.api_mbds.model.Connexion;
import com.tpt.api_mbds.model.Histo_jetons_View;
import com.tpt.api_mbds.model.Historique_Jetons;
import oracle.jdbc.OracleConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Historique_jetonsController {

    public Historique_jetonsController() {
    }

    public String insertHistorique(Historique_Jetons historique_jetons) throws SQLException {
        OracleConnection oracleConnection = null;
        Statement statement = null;
        String val="";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String strDate= formatter.format(date);
        System.out.println("Date strDate Androany "+strDate);
        try {
            oracleConnection = Connexion.getConnection();
            statement = oracleConnection.createStatement();
            String requete ="insert into historique_jetons colums(colums.idUtilisateur,colums.idTransaction,colums.dateTransaction,colums.montant,colums.idPari) values ("+historique_jetons.getIdUtilisateur()+","+historique_jetons.getIdTransaction()+",to_date('"+strDate+"','dd-MM-yyyy','NLS_DATE_LANGUAGE = American'),"+historique_jetons.getMontant()+","+historique_jetons.getIdPari()+")";
            System.out.println(requete);
            ResultSet resultSet = statement.executeQuery(requete);
            val="Insertion de Historique Jetons avec comme transaction "+historique_jetons.getIdTransaction();
            System.out.println(val);
        }catch (Exception e){
            throw e;
        }finally {
            if(oracleConnection!=null)oracleConnection.close();
        }
        return val;
    }

    public ArrayList<Histo_jetons_View> getAllHistorique_JetonsbyUser(int id) throws SQLException {

        ArrayList<Histo_jetons_View> list=new ArrayList<Histo_jetons_View>();
        Statement statement = null;
        OracleConnection oracleConnection = null;
        try{
            oracleConnection = Connexion.getConnection();
            statement = oracleConnection.createStatement();
            String request="select * from HISTO_TRANSAC where idutilisateur="+id+" order BY datetransaction asc";
            System.out.println(request);
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()){
                Histo_jetons_View val=new Histo_jetons_View();
                val.setId(resultSet.getInt(1));
                val.setIdUtilisateur(resultSet.getInt(2));
                val.setIdTransaction(resultSet.getString(3));
                val.setDateTransaction(resultSet.getDate(4));
                val.setMontant(resultSet.getInt(5));
                val.setIdPari(resultSet.getInt(6));
                list.add(val);
            }
        }
        finally{
            if(statement!=null)statement.close();
            if(oracleConnection!=null)oracleConnection.close();
        }
        return list;
    }

}
