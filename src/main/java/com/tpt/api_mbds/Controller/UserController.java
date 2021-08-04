package com.tpt.api_mbds.Controller;

import com.tpt.api_mbds.model.Connexion;
import com.tpt.api_mbds.model.Utilisateur;
import oracle.jdbc.OracleConnection;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UserController {

    public UserController() {
    }

    public static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<Utilisateur> getAllUser(OracleConnection co) throws SQLException {
        ArrayList<Utilisateur> list=new ArrayList<Utilisateur>();

        Statement statement = null;
        try{

            statement = co.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from UTILISATEUR ");
            while (resultSet.next()){
                Utilisateur val=new Utilisateur();
                val.setId(resultSet.getInt(1));
                val.setNom(resultSet.getString(2));
                val.setPrenom(resultSet.getString(3));
                val.setDateNaissance(resultSet.getDate(4));
                val.setPseudo(resultSet.getString(5));
                val.setPwd(resultSet.getString(6));
                val.setJetons(resultSet.getInt(7));
                val.setMail(resultSet.getString(8));
                list.add(val);
            }
        }
        finally{
            if(statement!=null)
                statement.close();
        }
        return list;
    }

    public Utilisateur authentification(OracleConnection co , String mail,String password) throws SQLException {
        Utilisateur val=new Utilisateur();
        Statement statement = null;
        try {

            statement = co.createStatement();
            String passwordHash=sha256(password);
            String requete ="select * from UTILISATEUR where mail='"+mail+"' and pwd='"+passwordHash+"'";
            ResultSet resultSet = statement.executeQuery("select * from UTILISATEUR where mail='"+mail+"' and pwd='"+passwordHash+"'");
            while (resultSet.next()){
                //System.out.println("ID AVANT " + resultSet.getInt(1));
                val.setId(resultSet.getInt(1));
                val.setNom(resultSet.getString(2));
                val.setPrenom(resultSet.getString(3));
                val.setDateNaissance(resultSet.getDate(4));
                val.setPseudo(resultSet.getString(5));
                //val.setPwd(resultSet.getString(6));
                val.setJetons(resultSet.getInt(7));
                val.setMail(resultSet.getString(8));
            }
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();
        }
        return val;

    }

    public Utilisateur chechUserMail(OracleConnection co , String mail) throws SQLException {
        Utilisateur val=new Utilisateur();
        Statement statement = null;
        try {

            statement = co.createStatement();
            String requete ="select * from UTILISATEUR where mail='"+mail+"'";
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()){
                //System.out.println("ID AVANT " + resultSet.getInt(1));
                val.setId(resultSet.getInt(1));
                val.setNom(resultSet.getString(2));
                val.setPrenom(resultSet.getString(3));
                val.setDateNaissance(resultSet.getDate(4));
                val.setPseudo(resultSet.getString(5));
                //val.setPwd(resultSet.getString(6));
                val.setJetons(resultSet.getInt(7));
                val.setMail(resultSet.getString(8));
            }
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();
        }
        return val;

    }

    public String insertUser(OracleConnection co , Utilisateur user) throws SQLException {
        Utilisateur val=new Utilisateur();
        Statement statement = null;
        try {
            statement = co.createStatement();
            String passwordHash=sha256(user.getPwd());
            //System.out.println("le mdp "+passwordHash);
            /*Date date = new Date();

            DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US); */

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String strDate= formatter.format(user.getDateNaissance()).toString();
            //System.out.println("Date strDate "+strDate);
            //String requete2 ="insert into PARI columns(columns.idutilisateur, columns.idmatch,columns.matchregle,columns.mise,columns.datepari)values ('"+pari.getIdUtilisateur()+"','"+pari.getIdMatch()+"','"+pari.getMatchRegle()+"',"+pari.getMise()+",'"+strDate+"')";
            String requete ="insert into UTILISATEUR columns(columns.nom, columns.prenom,columns.datenaissance,columns.pseudo,columns.pwd, columns.jetons,columns.mail) values ('"+user.getNom()+"','"+user.getPrenom()+"',to_date('"+strDate+"','dd-MM-yyyy','NLS_DATE_LANGUAGE = American'),'"+user.getPseudo()+"','"+passwordHash+"',0,'"+user.getMail()+"')";
            System.out.println(requete);
            ResultSet resultSet = statement.executeQuery(requete);
            return "Inscription réussie";
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();
        }
    }


    public String AjoutJeton( Integer jeton , Integer iduser) throws SQLException {
        Utilisateur val=new Utilisateur();
        Statement statement = null;
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            statement = oracleConnection.createStatement();
            Integer jetonsUserAvant=this.getjetonUser(oracleConnection,iduser);
            Integer JetonsApres=jetonsUserAvant+jeton;
            String requete ="update UTILISATEUR set jetons="+JetonsApres+" where id="+iduser+"";
            System.out.println(requete);
            ResultSet resultSet = statement.executeQuery(requete);
            return "Jetons Ajoute";
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();
            if(oracleConnection!=null) oracleConnection.close();
        }
    }


//////////////////////////Vrai Update fa tsy Manova jetons////////////////////////////
    public Utilisateur updateUser(Integer id, Utilisateur user) throws SQLException {
        Utilisateur val=new Utilisateur();
        Statement statement = null;
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            statement = oracleConnection.createStatement();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String strDate= formatter.format(user.getDateNaissance()).toString();

            String requete1 ="update UTILISATEUR set NOM='"+user.getNom()+"',PRENOM='"+user.getPrenom()+"',DATENAISSANCE=to_date('"+strDate+"','dd-MM-yyyy','NLS_DATE_LANGUAGE = American'),PSEUDO='"+user.getPseudo()+"',MAIL='"+user.getMail()+"' where id="+id+"";
            System.out.println(requete1);
            statement.executeQuery(requete1);
            String requete2="select * from UTILISATEUR where ID="+id;
            System.out.println(requete2);
            ResultSet resultSet = statement.executeQuery(requete2);


            while (resultSet.next()){
                val.setId(resultSet.getInt(1));
                val.setNom(resultSet.getString(2));
                val.setPrenom(resultSet.getString(3));
                val.setDateNaissance(resultSet.getDate(4));
                val.setPseudo(resultSet.getString(5));
                val.setJetons(resultSet.getInt(7));
                val.setMail(resultSet.getString(8));
            }
            return val;
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();
            if(oracleConnection!=null) oracleConnection.close();
        }
    }

    public Utilisateur getUserById(Integer idUser) throws SQLException {
        Utilisateur val=new Utilisateur();
        Statement statement = null;
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            statement = oracleConnection.createStatement();
            String requete2="select * from UTILISATEUR where ID="+idUser;

            System.out.println(requete2);
            ResultSet resultSet = statement.executeQuery(requete2);
            while (resultSet.next()){
                val.setId(resultSet.getInt(1));
                val.setNom(resultSet.getString(2));
                val.setPrenom(resultSet.getString(3));
                val.setDateNaissance(resultSet.getDate(4));
                val.setPseudo(resultSet.getString(5));
                val.setJetons(resultSet.getInt(7));
                val.setMail(resultSet.getString(8));
            }
            return val;
        }catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();
            if(oracleConnection!=null) oracleConnection.close();
        }

    }


    public String EnleveJeton( Integer jeton , Integer iduser) throws SQLException {
        Utilisateur val=new Utilisateur();
        Statement statement = null;
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            statement = oracleConnection.createStatement();
            Integer jetonsUserAvant=this.getjetonUser(oracleConnection,iduser);
            Integer JetonsApres=jetonsUserAvant-jeton;
            String requete ="update UTILISATEUR set jetons="+JetonsApres+" where id="+iduser+"";
            System.out.println(requete);
            ResultSet resultSet = statement.executeQuery(requete);
            return "Jetons Enleve";
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();
            if(oracleConnection!=null) oracleConnection.close();
        }
    }

    public Integer getjetonUser(OracleConnection co , Integer iduser) throws SQLException {
        Integer val=0;
        Statement statement = null;

        try {
            statement = co.createStatement();

            String requete ="select jetons from UTILISATEUR where id="+iduser+"";
            System.out.println(requete);
            ResultSet resultSet = statement.executeQuery(requete);
            while(resultSet.next()){
                val=resultSet.getInt(1);
            }
            return val;
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();

        }
    }
    public boolean testJetonSuffisant(Integer jetonsAMiser,Integer idUser) throws SQLException {
        boolean val=false;
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            Integer jetonsJoueur = this.getjetonUser(oracleConnection,idUser);
            if(jetonsJoueur>=jetonsAMiser){val=true;}

        }catch (Exception e){
            throw e;
        }
        finally {
            if(oracleConnection!=null)oracleConnection.close();
        }
        return val;

    }

}
