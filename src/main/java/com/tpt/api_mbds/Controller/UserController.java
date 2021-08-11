package com.tpt.api_mbds.Controller;

import com.tpt.api_mbds.model.Connexion;
import com.tpt.api_mbds.model.Jeton;
import com.tpt.api_mbds.model.Utilisateur;
import com.tpt.api_mbds.repository.JetonRepository;
import oracle.jdbc.OracleConnection;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {


    JetonRepository jetonRepository;
    @Autowired
    public UserController(JetonRepository jetonRepository) {
        this.jetonRepository = jetonRepository;
        this.jetonRepository.findAll();
    }

    public UserController() {
    }

    JetonController jetonController=new JetonController();

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
                val.setSolde(resultSet.getDouble(9));
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
                val.setSolde(resultSet.getDouble(9));
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
                val.setSolde(resultSet.getDouble(9));
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
            String requete ="insert into UTILISATEUR columns(columns.nom, columns.prenom,columns.datenaissance,columns.pseudo,columns.pwd, columns.jetons,columns.mail,columns.solde) values ('"+user.getNom()+"','"+user.getPrenom()+"',to_date('"+strDate+"','dd-MM-yyyy','NLS_DATE_LANGUAGE = American'),'"+user.getPseudo()+"','"+passwordHash+"',0,'"+user.getMail()+"',10000)";
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
////////////////////////////ITO ACHAT AVEC SOLDE /////////////////////////////////
    public String AchatJeton(OracleConnection oracleConnection, Integer jeton ,Double montantTotal, Integer iduser) throws SQLException {

        Statement statement = null;

        try {

            statement = oracleConnection.createStatement();
            Integer jetonsUserAvant=this.getjetonUser(oracleConnection,iduser);
            Double soldeUserAvant =this.getSoldeUser(oracleConnection,iduser);
            if(soldeUserAvant<montantTotal) {

                return "Solde insuffisant , votre solde "+soldeUserAvant+ " montant total des jetons : "+montantTotal;
            }
            Integer jetonsApres=jetonsUserAvant+jeton;
            String requete ="update UTILISATEUR set jetons="+jetonsApres+" where id="+iduser+"";
            System.out.println(requete);

            Double soldeapres = soldeUserAvant-montantTotal;
            String requete2 ="update UTILISATEUR set solde="+soldeapres+" where id="+iduser+"";
            System.out.println(requete2);
            statement.executeQuery(requete);
            statement.executeQuery(requete2);
            return jeton+" jetons ajouté , reste de votre solde "+soldeapres;
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();

        }
    }
    ////////////////////////////ITO Vente AVEC SOLDE /////////////////////////////////
    public String VenteJeton(OracleConnection oracleConnection, Integer jeton ,Double montantTotal, Integer iduser) throws SQLException {

        Statement statement = null;

        try {

            statement = oracleConnection.createStatement();
            Integer jetonsUserAvant=this.getjetonUser(oracleConnection,iduser);
            Double soldeUserAvant =this.getSoldeUser(oracleConnection,iduser);
            if(jetonsUserAvant<jeton) {

                return "Jetons insuffisants , vos Jetons "+jetonsUserAvant+ "  jetons a vendre: "+jeton;
            }
            Integer jetonsApres=jetonsUserAvant-jeton;
            String requete ="update UTILISATEUR set jetons="+jetonsApres+" where id="+iduser+"";
            System.out.println(requete);

            Double soldeapres = soldeUserAvant+montantTotal;
            String requete2 ="update UTILISATEUR set solde="+soldeapres+" where id="+iduser+"";
            System.out.println(requete2);
            statement.executeQuery(requete);
            statement.executeQuery(requete2);
            return jeton+" jetons vendu , votre solde "+soldeapres;
        }
        catch (Exception e){
            throw e;
        }
        finally{
            if(statement!=null)
                statement.close();

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
                val.setSolde(resultSet.getDouble(9));
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

    public void updateUserMdp(Integer id, String mdp) throws SQLException {

        Statement statement = null;
        OracleConnection oracleConnection = null;
        try {
            oracleConnection = Connexion.getConnection();
            statement = oracleConnection.createStatement();
            String passwordHash=sha256(mdp);

            String requete1 ="update UTILISATEUR set PWD='"+passwordHash+"' where id="+id+"";
            System.out.println(requete1);
            statement.executeQuery(requete1);
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
                val.setSolde(resultSet.getDouble(9));
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

    public double getSoldeUser(OracleConnection co , Integer iduser) throws SQLException {
        double val=0;
        Statement statement = null;

        try {
            statement = co.createStatement();

            String requete ="select solde from UTILISATEUR where id="+iduser+"";
            System.out.println(requete);
            ResultSet resultSet = statement.executeQuery(requete);
            while(resultSet.next()){
                val=resultSet.getDouble(1);
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


    public boolean testSoldeSuffisant(OracleConnection oracleConnection,Integer jetonsTobuy,Double montantTotal,Integer idUser) throws SQLException {
        boolean val=false;

        try {

            double soldeJoueur = this.getSoldeUser(oracleConnection,idUser);
            System.out.println("ITO ILAY SOLDE "+soldeJoueur);
            if(soldeJoueur>=montantTotal){val=true;}
        }catch (Exception e){
            throw e;
        }

        return val;

    }

}
