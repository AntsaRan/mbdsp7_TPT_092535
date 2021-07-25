package com.tpt.api_mbds.Controller;

import com.tpt.api_mbds.model.SuperAdmin;
import com.tpt.api_mbds.model.Utilisateur;
import oracle.jdbc.OracleConnection;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SuperAdminController {

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


    public SuperAdmin authentification(OracleConnection co , String pseudo, String password) throws SQLException {
        SuperAdmin val=new SuperAdmin();
        Statement statement = null;
        try {

            statement = co.createStatement();
            String passwordHash=sha256(password);
           //DP "+passwordHash);
            String requete ="select * from SUPERADMIN where pseudo='"+pseudo+"' and password='"+passwordHash+"'";
            System.out.println(requete);
            ResultSet resultSet = statement.executeQuery(requete);
            while (resultSet.next()){
                //System.out.println("ID AVANT " + resultSet.getInt(1));
                val.setId(resultSet.getInt(1));
                val.setPseudo(resultSet.getString(2));
                val.setMail(resultSet.getString(4));
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

}
