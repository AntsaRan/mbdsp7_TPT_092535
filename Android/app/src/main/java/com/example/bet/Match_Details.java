package com.example.bet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bet.API.API;
import com.example.bet.modele.Equipe;
import com.example.bet.modele.Equipe_Response;
import com.example.bet.modele.Match;
import com.example.bet.modele.MatchRegle;
import com.example.bet.modele.MatchRegle_Response;
import com.example.bet.modele.RequestPari;
import com.example.bet.modele.Utilisateur;
import com.example.bet.service.Equipe_Service;
import com.example.bet.service.MatchRegle_Service;
import com.example.bet.service.Paris_Service;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Match_Details extends AppCompatActivity {

    TextView score1, score2, nomTeam1, nomTeam2, etat, lieu, corner1, corner2, possession1, possession2;
    ImageView img1, img2;
    ProgressBar bar, bar2;
    MatchRegle_Service service;
    Paris_Service service_Pari;

    /////
    TextView quote1, quote2, quote3, quote4, quote5, quote6, quote7, quote8, quote9;
    TextView titre1, titre2, titre3, label_mise, label_jetons;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9,buttonParier;
    ImageView background1, background2, background3, background_mini1, background_mini2, background_mini3, background_mise;

    ImageButton close;
    EditText nbr_mise;
    List<TextView> listQuotes;
    List<Button> listButton;
    List<TextView> listTitre;
    Utilisateur utilisateur;
    SharedPreferences pref;

    String idMatch;
    String idMatchRegle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match__details);
        Match match = (Match) getIntent().getExtras().getParcelable("match");
        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        nomTeam1 = (TextView) findViewById(R.id.nomEquipe1);
        nomTeam2 = (TextView) findViewById(R.id.nomEquipe2);
        corner1 = (TextView) findViewById(R.id.corner1);
        corner2 = (TextView) findViewById(R.id.corner2);
        possession1 = (TextView) findViewById(R.id.possession1);
        possession2 = (TextView) findViewById(R.id.possession2);
        lieu = (TextView) findViewById(R.id.etat);
        etat = (TextView) findViewById(R.id.lieu);
        //Set Text
        score1.setText(String.valueOf(match.getScoreEquipe1()));
        score2.setText(String.valueOf(match.getScoreEquipe2()));
        nomTeam1.setText(match.getEquipe1().getNom());
        nomTeam2.setText(match.getEquipe2().getNom());
        corner1.setText(String.valueOf(match.getCornerEquipe1()));
        corner2.setText(String.valueOf(match.getCornerEquipe2()));
        possession1.setText(String.valueOf(match.getPossessionEquipe1()) + "%");
        possession2.setText(String.valueOf(match.getPossessionEquipe2()) + "%");
        lieu.setText(match.getEtat());
        etat.setText(match.getLieu());
        ////---------------------///////

        quote1 = findViewById(R.id.quote1);
        quote2 = findViewById(R.id.quote2);
        quote3 = findViewById(R.id.quote3);
        quote4 = findViewById(R.id.quote4);
        quote5 = findViewById(R.id.quote5);
        quote6 = findViewById(R.id.quote6);
        quote7 = findViewById(R.id.quote7);
        quote8 = findViewById(R.id.quote8);
        quote9 = findViewById(R.id.quote9);

        titre1 = findViewById(R.id.titre1);
        titre2 = findViewById(R.id.titre2);
        titre3 = findViewById(R.id.titre3);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        background1 = findViewById(R.id.background1);
        background2 = findViewById(R.id.background2);
        background3 = findViewById(R.id.background3);

        background_mini1 = findViewById(R.id.background_mini1);
        background_mini2 = findViewById(R.id.background_mini2);
        background_mini3 = findViewById(R.id.background_mini3);

        titre1=findViewById(R.id.titre1);
        titre2=findViewById(R.id.titre2);
        titre3=findViewById(R.id.titre3);
        bar=findViewById(R.id.progressBar);
        bar2=findViewById(R.id.progressBarPari);
        bar2.setVisibility(View.GONE);

        buttonParier=findViewById(R.id.btnParier);
        label_mise=findViewById(R.id.label_mise);
        label_jetons=findViewById(R.id.label_jetons);
        nbr_mise=findViewById(R.id.nbr_mise);
        background_mise=findViewById(R.id.mise_bg);
        close=findViewById(R.id.close);

        buttonParier.setVisibility(View.GONE);
        label_mise.setVisibility(View.GONE);
        label_jetons.setVisibility(View.GONE);
        nbr_mise.setVisibility(View.GONE);
        background_mise.setVisibility(View.GONE);
        close.setVisibility(View.GONE);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonParier.setVisibility(View.GONE);
                label_mise.setVisibility(View.GONE);
                nbr_mise.setVisibility(View.GONE);
                background_mise.setVisibility(View.GONE);
                close.setVisibility(View.GONE);
            }
        });

        buttonParier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar2.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(nbr_mise.getText().toString())) {
                    //Toast.makeText(Login.this, "Veuillez saisir votre email/password", Toast.LENGTH_SHORT).show();
                    showError(nbr_mise, "Mise invalide");
                }
                else{
                    if(testMise(nbr_mise.getText().toString())){
                        System.out.println("ID MATCHREGLE "+idMatchRegle);
                        System.out.println("MISE "+Float.valueOf(nbr_mise.getText().toString()));
                        insertPari(utilisateur.getId(),idMatch,idMatchRegle,Float.valueOf(nbr_mise.getText().toString())).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(Match_Details.this, "Validation du Pari reussie", Toast.LENGTH_SHORT).show();
                                bar2.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(Match_Details.this, "Validation du Pari echoué", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            }
        });
        ////---------------------///////

        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        if(pref.contains("user") ){
            Gson gson = new Gson();
            String json = pref.getString("user", "");
             utilisateur = gson.fromJson(json, Utilisateur.class);
        }
        idMatch = match.getId();
        service = API.getClient().create(MatchRegle_Service.class);
        service_Pari = API.getClient().create(Paris_Service.class);
        System.out.println("ID DE MATCH"+idMatch);
        Initialise();
        DisableButton();
        loadFirstPage();
       /* Picasso.get()
                .load(post_URL+intent.getStringExtra("bannerpath"))
                .into(banner);
        Picasso.get()
                .load(back_URL+intent.getStringExtra("profilepath"))
                .into(profil);

        Log.e("MATCH ITOOOO", "ALLO : " + match.getLieu());
        */
    }

    @Override
    protected void onStart() {
        super.onStart();
        service = API.getClient().create(MatchRegle_Service.class);
    }

    private void loadFirstPage() {

        callToGetRegles(idMatch).enqueue(new Callback<MatchRegle_Response>() {


            @Override
            public void onResponse(Call<MatchRegle_Response> call, retrofit2.Response<MatchRegle_Response> response) {

               final List<MatchRegle> results = fetchResults(response);

                for( int i=0;i<results.get(0).getRegles().size();i++){
                    final int cmpt=i;
                    listQuotes.get(i).setText(String.valueOf(results.get(0).getRegles().get(i).getCote()));
                    listButton.get(i).setText(String.valueOf(results.get(0).getRegles().get(i).getRegle().getDefinition()));
                    listButton.get(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            buttonParier.setVisibility(View.VISIBLE);
                            label_mise.setVisibility(View.VISIBLE);
                            nbr_mise.setVisibility(View.VISIBLE);
                            background_mise.setVisibility(View.VISIBLE);
                            label_jetons.setVisibility(View.VISIBLE);
                            close.setVisibility(View.VISIBLE);
                            label_jetons.setText("Vos jetons :"+utilisateur.getJetons());

                            idMatchRegle=results.get(0).getRegles().get(cmpt).getRegle().getId();
                            System.out.println("Regle ID "+results.get(0).getRegles().get(cmpt).getRegle().getId());
                        }
                    });

                }
                EnableButton();
                System.out.println("RESULTS "+results.get(0).getRegles().size());
                bar.setVisibility(View.GONE);


            }


            @Override
            public void onFailure(Call<MatchRegle_Response> call, Throwable t) {
                t.printStackTrace();
                Log.e("EXCEPTION ATO ", t.getMessage());
            }
        });

    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
    private List<MatchRegle> fetchResults(retrofit2.Response<MatchRegle_Response> response) {
        System.out.println("Ito le response" + response.message());
        MatchRegle_Response allRegles = response.body();
        return allRegles.getResults();
    }


    private Call<MatchRegle_Response> callToGetRegles(String _id) {
        return service.getByID(_id);
    }
    private Call<MatchRegle_Response> callToGetReglesAll( ) {
        return service.getAllMatchesRegle();
    }

    private Call<ResponseBody> insertPari(String idUtilisateur,String idMatch,String idMatchRegle,float mise) {
        RequestPari pari=new RequestPari(idUtilisateur,idMatch,idMatchRegle,mise);
        return service_Pari.insertPari(pari);
        // return service_Pari.insertPari(idUtilisateur,idMatch,idMatchRegle,mise);
    }
    //Fonctions pour initialisation
    public void Initialise() {
        listButton = new ArrayList<Button>();
        listQuotes = new ArrayList<>();
        listTitre = new ArrayList<>();

        listTitre.add(titre1);
        listTitre.add(titre2);
        listTitre.add(titre3);

        listQuotes.add(quote1);
        listQuotes.add(quote2);
        listQuotes.add(quote3);
        listQuotes.add(quote4);
        listQuotes.add(quote5);
        listQuotes.add(quote6);
        listQuotes.add(quote7);
        listQuotes.add(quote8);
        listQuotes.add(quote9);

        listButton.add(button1);
        listButton.add(button2);
        listButton.add(button3);
        listButton.add(button4);
        listButton.add(button5);
        listButton.add(button6);
        listButton.add(button7);
        listButton.add(button8);
        listButton.add(button9);
    }

    public void DisableButton() {
        if (!listButton.isEmpty()) {
            for (int i = 0; i<listButton.size();i++) {
                listButton.get(i).setVisibility(View.GONE);
            }
        }
    }
    public void EnableButton() {
        if (!listButton.isEmpty()) {
            for (int i = 0; i<listButton.size();i++) {
                listButton.get(i).setVisibility(View.VISIBLE);
            }
        }
    }

    public boolean testMise(String mise){
        int val= Integer.valueOf(mise);
        if(utilisateur.getJetons()-val>=0){
            return true;
        }
        return false;
    }
}
