package com.tpt.api_mbds.model;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;


public class Connexion {
    final static String DB_URL= "jdbc:oracle:thin:@tcps://adb.uk-london-1.oraclecloud.com:1522/g07cec4cf312e8f_tptmbds_tp.adb.oraclecloud.com?wallet_location=Wallet_tptmbds&oracle.net.ssl_server_cert_dn=\"CN=adwc.eucom-central-1.oraclecloud.com, OU=Oracle BMCS FRANKFURT, O=Oracle Corporation, L=Redwood City, ST=California, C=US\"";
    final static String DB_USER = "ADMIN";
    final static String DB_PASSWORD = "Antsaranari1!";

    public static OracleConnection getConnection() throws SQLException{
        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");


        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_URL);
        ods.setConnectionProperties(info);

        OracleConnection connection = (OracleConnection) ods.getConnection();
        System.out.println("Vita");
        return connection;
    }


}