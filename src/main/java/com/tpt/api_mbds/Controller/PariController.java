package com.tpt.api_mbds.Controller;

import com.tpt.api_mbds.model.Pari;
import oracle.jdbc.OracleConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PariController {

    public PariController() {
    }

    public void insertPari(OracleConnection co , Pari pari) throws SQLException {
        Pari val=new Pari();
        Statement statement = null;
        try {
            statement = co.createStatement();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
            String strDate= formatter.format(date);
            System.out.println("Date strDate "+strDate);
            //String requete2 ="insert into PARI columns(columns.idutilisateur, columns.idmatch,columns.matchregle,columns.mise,columns.datepari)values ('"+pari.getIdUtilisateur()+"','"+pari.getIdMatch()+"','"+pari.getMatchRegle()+"',"+pari.getMise()+",'"+strDate+"')";
            String requete ="insert into PARI columns(columns.idutilisateur, columns.idmatch,columns.matchregle,columns.mise,columns.datepari)values ('"+pari.getIdUtilisateur()+"','"+pari.getIdMatch()+"','"+pari.getMatchRegle()+"',"+pari.getMise()+",to_date('"+strDate+"','dd-mm-yyyy','NLS_DATE_LANGUAGE = American'))";
            System.out.println(requete);
            ResultSet resultSet = statement.executeQuery(requete);
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();
        }
    }

    public ArrayList<Pari> getAllParis(OracleConnection co) throws SQLException {

        ArrayList<Pari> list=new ArrayList<Pari>();
        Statement statement = null;
        try{
            statement = co.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from PARI");
            while (resultSet.next()){
                Pari val=new Pari();
                val.setId(resultSet.getInt(1));
                val.setIdUtilisateur(resultSet.getInt(2));
                val.setIdMatch(resultSet.getString(3));
                val.setMatchRegle(resultSet.getString(4));
                val.setMise(resultSet.getFloat(5));
                val.setDateParis(resultSet.getDate(6));
                list.add(val);
            }
        }
        finally{
            if(statement!=null)
                statement.close();
        }
        return list;
    }

    public ArrayList<Pari> getAllParisbyUser(OracleConnection co,int id) throws SQLException {

        ArrayList<Pari> list=new ArrayList<Pari>();
        Statement statement = null;
        try{
            statement = co.createStatement();
            String request="select * from PARI where IDUTILISATEUR="+id;
            System.out.println(request);
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()){
                Pari val=new Pari();
                val.setId(resultSet.getInt(1));
                val.setIdUtilisateur(resultSet.getInt(2));
                val.setIdMatch(resultSet.getString(3));
                val.setMatchRegle(resultSet.getString(4));
                val.setMise(resultSet.getFloat(5));
                val.setDateParis(resultSet.getDate(6));
                list.add(val);
            }
        }
        finally{
            if(statement!=null)
                statement.close();
        }
        return list;
    }


}
