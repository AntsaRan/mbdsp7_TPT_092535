package com.example.bet.controleur;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import com.example.bet.R;
import com.example.bet.model.Equipe;
import com.example.bet.model.Match;
import com.example.bet.model.Utilisateur;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "Utilisateur";
    private static final String col1 = "id";

    private static final String col2 = "nom";
    private static final String col3 = "prenom";
    private static final String col4 = "dateNaissance";
    private static final String col5 = "pseudo";
    private static final String col6 = "pwd";
    private static final String col7 = "jetons";
    private static final String col8 = "mail";
    private static final String col9 = "solde";
    public DataBaseHelper(Context context){
        super(context,TABLE_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String creataTable = "CREATE TABLE " + TABLE_NAME + "(" + col1 + " TEXT,"+col2+" TEXT,"+col3+" TEXT,"+col4+" TEXT,"+col5+" TEXT,"+col6+" TEXT,"+col7+" TEXT,"+col8+" TEXT,"+col9+" TEXT)";
        db.execSQL(creataTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }
    public boolean insertToken (String token){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,token);
        Log.d(TAG,"insertToken : " + token);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return false;
        }
        else
        {
            return true;

        }

    }   public void deletetablematchs(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Matchs");

    }
    public void initializeoffline(){
        SQLiteDatabase db = this.getWritableDatabase();
        String createofflinetable = "CREATE TABLE IF NOT EXISTS Matchs ( id  TEXT ,equipe1ID TEXT,equipe1NOM TEXT,equipe2ID TEXT,equipe2NOM TEXT,date TEXT,lieu TEXT, etat TEXT, scoreEquipe1 TEXT, scoreEquipe2 TEXT, cornerEquipe1 TEXT, cornerEquipe2 TEXT, possessionEquipe1 TEXT, possessionEquipe2 TEXT,logo1 TEXT,logo2 TEXT)";
        db.execSQL(createofflinetable);
    }
    public void initializetoken(){
        SQLiteDatabase db = this.getWritableDatabase();
        String creataTable = "CREATE TABLE " + TABLE_NAME + "(" + col1 + " TEXT)";


        db.execSQL(creataTable);
    }
    public void insertofflinematch (List<Match> listmatchs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        for(int i = 0;i<listmatchs.size();i++){
            contentValues.put("id",listmatchs.get(i).getId());
            contentValues.put("equipe1ID",listmatchs.get(i).getEquipe1().getId());
            contentValues.put("equipe1NOM",listmatchs.get(i).getEquipe1().getNom());
            contentValues.put("equipe2ID",listmatchs.get(i).getEquipe2().getId());
            contentValues.put("equipe2NOM",listmatchs.get(i).getEquipe2().getNom());
            contentValues.put("date",listmatchs.get(i).getDate().toString());
            contentValues.put("lieu",listmatchs.get(i).getLieu().toString());
            contentValues.put("etat",listmatchs.get(i).getEtat().toString());
            contentValues.put("scoreEquipe1",String.valueOf(listmatchs.get(i).getScoreEquipe1()));
            contentValues.put("scoreEquipe2",String.valueOf(listmatchs.get(i).getScoreEquipe2()));
            contentValues.put("possessionEquipe1",String.valueOf(listmatchs.get(i).getPossessionEquipe1()));
            contentValues.put("possessionEquipe2",String.valueOf(listmatchs.get(i).getPossessionEquipe1()));
            contentValues.put("cornerEquipe1",String.valueOf(listmatchs.get(i).getCornerEquipe1()));
            contentValues.put("cornerEquipe2",String.valueOf(listmatchs.get(i).getCornerEquipe2()));
            contentValues.put("logo1", R.drawable.image_placeholder);
            contentValues.put("logo2",R.drawable.image_placeholder);
            db.insert("Matchs",null,contentValues);

        }
        Log.e("INSERTED","Offline Table initialized");


    }

    public void deleteToken(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;
        db.execSQL(query);
    }
    public void deletematch(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Matchs";
        db.execSQL(query);
    }
    public void deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+TABLE_NAME;
        db.execSQL(query);
    }
    public String getToken(){
        SQLiteDatabase dblite = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = dblite.rawQuery(query,null);
        String datastring      = null;

        if (data.moveToFirst()) {
            do {
                datastring =  data.getString(0);
            } while (data.moveToNext());
        }
        data.close();
        return datastring ;
    }

    public void initializeUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String creataTable = "CREATE TABLE " + TABLE_NAME + "(" + col1 + " TEXT,"+col2+" TEXT,"+col3+" TEXT,"+col4+" TEXT,"+col5+" TEXT,"+col6+" TEXT,"+col7+" TEXT,"+col8+" TEXT,"+col9+" TEXT)";


        db.execSQL(creataTable);
    }
    public void insertUtilisateur(Utilisateur utilisateur){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",utilisateur.getId());
        contentValues.put("nom",utilisateur.getNom());
        contentValues.put("prenom",utilisateur.getPrenom());
        contentValues.put("dateNaissance",utilisateur.getDateNaissance().toString());
        contentValues.put("pseudo",utilisateur.getPseudo());
        contentValues.put("pwd",utilisateur.getPwd());
        contentValues.put("jetons",utilisateur.getJetons());
        contentValues.put("mail",utilisateur.getMail());
        contentValues.put("solde",utilisateur.getSolde());
        db.insert("Utilisateur",null,contentValues);
        Log.e("INSERTED","Offline Utilisateur Table initialized");
    }

    public void insertEquipe(Equipe equipe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",equipe.getId());
        contentValues.put("nom",equipe.getNom());
        contentValues.put("prenom",equipe.getLogo());

        db.insert("Equipe",null,contentValues);
        Log.e("INSERTED","Offline Equipe Table initialized");
    }
    public void initializeEquipe(){
        SQLiteDatabase db = this.getWritableDatabase();
        String creataTable = "CREATE TABLE IF NOT EXISTS Equipe ( id  TEXT ,nom TEXT,logo TEXT)";


        db.execSQL(creataTable);
    }
    public Utilisateur getUtilisateur(){
        SQLiteDatabase dblite = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = dblite.rawQuery(query,null);
        Utilisateur utilisateur=null;
        if (data.moveToFirst()) {
            do {
               utilisateur=new Utilisateur();
                utilisateur.setId(data.getString(0));
                utilisateur.setNom(data.getString(1));
                utilisateur.setPrenom(data.getString(2));
                utilisateur.setDateNaissance(data.getString(3));
                utilisateur.setPseudo(data.getString(4));
                utilisateur.setPwd(data.getString(5));
                utilisateur.setJetons(Float.valueOf(data.getString(6)));
                utilisateur.setMail(data.getString(7));
                utilisateur.setSolde(Float.valueOf(data.getString(8)));
            } while (data.moveToNext());
        }
        data.close();
        return utilisateur ;
    }

    public List<Match> getOfflineMatch(){
        SQLiteDatabase dblite = this.getReadableDatabase();
        String query = "SELECT * FROM Matchs";
        Cursor data = dblite.rawQuery(query,null);
        List<Match> listmatches = new ArrayList<>() ;
        try {
            if (data.moveToFirst()) {
                do {
                    Match instance = new Match();
                    Equipe equipe1 = new Equipe();
                    Equipe equipe2 = new Equipe();
                    equipe1.setId(data.getString(1));
                    equipe1.setNom(data.getString(2));
                    equipe2.setId(data.getString(3));
                    equipe2.setNom(data.getString(4));

                    equipe1.setLogo(data.getString(12));
                    equipe1.setLogo(data.getString(13));

                    instance.setId(data.getString(0));
                    instance.setEquipe1(equipe1);
                    instance.setEquipe2(equipe2);

                    instance.setLieu(data.getString(6));

                    String myFormat = "EEE MMM dd HH:mm:ss zzz yyyy"; //In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

                    System.out.println("DATE" + data.getString(5));
                    Date _date=  sdf.parse(data.getString(5));
                    instance.setDate(_date);
                    instance.setEtat(data.getString(7));

                    instance.setScoreEquipe1(Integer.valueOf(data.getString(8)));
                    instance.setScoreEquipe2(Integer.valueOf(data.getString(9)));

                    instance.setCornerEquipe1(Integer.valueOf(data.getString(10)));
                    instance.setCornerEquipe2(Integer.valueOf(data.getString(11)));

                    instance.setPossessionEquipe1(Integer.valueOf(data.getString(10)));
                    instance.setPossessionEquipe1(Integer.valueOf(data.getString(11)));


                    listmatches.add(instance);
                } while (data.moveToNext());
            }
            data.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return listmatches ;
    }
}
